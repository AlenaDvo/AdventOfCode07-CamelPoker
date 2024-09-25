package part1;

public enum HandType {

    FIVEOFAKIND(7),
    FOUROFAKIND(6),
    FULLHOUSE(5),
    THREEOFAKIND(4),
    TWOPAIR(3),
    ONEPAIR(2),
    HIGHCARD(1);

    private final int strength;


    HandType(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }
}