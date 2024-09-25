package part1;

import java.util.Arrays;

public enum CardLabel {
    A('A', 14),
    K('K', 13),
    Q('Q', 12),
    J('J', 11),
    T('T', 10),
    NINE('9', 9),
    EIGHT('8', 8),
    SEVEN('7', 7),
    SIX('6', 6),
    FIVE('5', 5),
    FOUR('4', 4),
    THREE('3', 3),
    TWO('2', 2);

    CardLabel(Character label, int strength) {
        this.label = label;
        this.strength = strength;
    }

    private final Character label;
    private final int strength;

    public Character getLabel() {
        return label;
    }

    public int getStrength() {
        return strength;
    }

    public static CardLabel parse(Character character) {
        return Arrays.stream(CardLabel.values())
                .filter(c -> c.getLabel().equals(character))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown CardLabel.label: " + character)));
    }
}
