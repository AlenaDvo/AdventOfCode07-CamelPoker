package part1;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Hand implements Iterable<Card> {

    private final List<Card> cards;
    private final int bid;
    private final HandType handType;
    private int rank;

    public int getRank() {
        return rank;
    }

    public Hand(List<Card> cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        this.handType = getHandType(cards);
        this.rank = 0;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    public HandType getHandType() {
        return handType;
    }

    public static Hand parse(String line) {
        String[] splitLine = line.strip().split(" ");

        String cardsPart = splitLine[0];
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardsPart.length(); i++) {
            CardLabel cardLabel = CardLabel.parse(Character.valueOf(cardsPart.charAt(i)));
            Card card = new Card(cardLabel);
            cards.add(card);
        }

        int bid = Integer.parseInt(splitLine[1]);

        return new Hand(cards, bid);
    }

    public void setRank(int rankToBeSet) {
        rank = rankToBeSet;
    }

    public static HandType getHandType(List<Card> cards) {
        Map<CardLabel, Integer> handTypeMap = new HashMap<>();

        for (Card card : cards) {
            CardLabel cardLabel = card.getCardLabel();
            if (!handTypeMap.containsKey(cardLabel)) {
                handTypeMap.put(cardLabel, 1);
            } else {
                handTypeMap.put(cardLabel, handTypeMap.get(cardLabel) + 1);
            }
        }

        List<Integer> labelsCount = new ArrayList<>(handTypeMap.values().stream().toList());

        if (labelsCount.contains(5)) {
            return HandType.FIVEOFAKIND;
        } else if (labelsCount.contains(4)) {
            return HandType.FOUROFAKIND;
        } else if (labelsCount.contains(3) && labelsCount.contains(2)) {
            return HandType.FULLHOUSE;
        } else if (labelsCount.contains(3)) {
            return HandType.THREEOFAKIND;
        } else if (labelsCount.contains(2)) {
            labelsCount.remove((Integer) 2);
            if (labelsCount.contains(2)) {
                return HandType.TWOPAIR;
            } else {
                return HandType.ONEPAIR;
            }
        } else {
            return HandType.HIGHCARD;
        }
    }

    public int compareTo(Hand otherHand) {
        List<Card> otherCards = otherHand.getCards();
        for (int i = 0; i < cards.size(); i++) {
            Card thisCard = cards.get(i);
            Card otherCard = otherCards.get(i);
            if (!thisCard.isSame(otherCard)) {
                if (thisCard.isStronger(otherCard)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }


    @Override
    public Iterator<Card> iterator() {
        return cards.stream().iterator();
    }

    @Override
    public void forEach(Consumer<? super Card> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public String toString() {
        return "Hand: " +
                "cards = " + cards +
                " " + bid +
                " " + rank +
                '}';
    }

    public Stream<Card> stream() {
        return cards.stream();
    }
}