package part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static part1.Hand.getHandType;

public class CamelPoker {
    SortedMap<Integer, List<Hand>> handsByType = new TreeMap<>();

    public Integer calculateWinnings(String fileName) throws Exception {

        List<Hand> hands = loadHands(fileName);
        for (Hand hand : hands) {
            Integer handStrength = getHandType(hand.getCards()).getStrength();
            handsByType.putIfAbsent(handStrength, new ArrayList<>());
            handsByType.get(handStrength).add(hand);
        }

        calculateRank();

        int winnings = 0;
        for (Hand hand : hands) {
            winnings += hand.getBid() * hand.getRank();
        }

        System.out.println(winnings);
        return winnings;
    }

    private void calculateRank() {
        int rank = 1;
        for (List<Hand> handList : handsByType.values()) {
            handList.sort(Hand::compareTo);
            for (Hand hand : handList) {
                hand.setRank(rank);
                rank++;
            }
        }
    }

    private static List<Hand> loadHands(String fileName) throws Exception {
        Reader reader = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(reader);
        return buffer
                .lines()
                .map(Hand::parse)
                .collect(Collectors.toList());
    }
}