package part1;

public class Card {
    private final CardLabel cardLabel;

    public Card(CardLabel cardLabel) {
        this.cardLabel = cardLabel;
    }

    public CardLabel getCardLabel() {
        return cardLabel;
    }

    public boolean isSame(Card otherCard) {
        return getCardLabel() == otherCard.getCardLabel();
    }

    public boolean isStronger(Card otherCard) {
        return getCardLabel().getStrength() > otherCard.getCardLabel().getStrength();
    }

    @Override
    public String toString() {
        return " " + cardLabel;
    }
}
