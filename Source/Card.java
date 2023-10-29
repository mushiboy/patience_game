/**
 * Card.java - Models a playing card.
 */

/**
 * A {@code Card} object represents a playing card.
 */
public class Card {
    private final Value value;
    private final Suit suit;
    private boolean showing;

    /**
     * Constructor for the class.
     *
     * @param value the value of the card (A-K)
     * @param suit  the suit of the card (H, D, C, S)
     */
    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
        this.showing = false;
    }

    /**
     * @return the value of the card
     */
    public Value getValue() {
        return value;
    }

    /**
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @return whether the card is showing
     */
    public boolean isShowing() {
        return showing;
    }

    /**
     * @param showing whether the card is showing
     */
    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    /**
     * @return the ordinal value of the card.
     */
    public int ordinal() {
        return this.value.ordinal();
    }

    /**
     * @return whether the card is red.
     */
    public boolean isRed() {
        return this.suit.isRed();
    }

    /**
     * Returns a string representation of the card.
     *
     * @return string representing the card
     */
    @Override
    public String toString() {
        if (this.showing)
            return "[" + this.value + this.suit + "]";
        else
            return "[  ]";
    }

}
