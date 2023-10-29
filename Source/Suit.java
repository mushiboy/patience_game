/**
 * Suit.java - Enum class for the different suits of playing card.
 */

/**
 * Suits of playing card.
 */
public enum Suit {
    HEARTS('H', true), DIAMONDS('D', true), CLUBS('C', false), SPADES('S', false);

    private char abbr; // abbreviation of the suit.
    private boolean red;

    /**
     * enum constructor for Suit
     *
     * @param abbr the abbreviation of the suit
     * @param red  whether the suit is red
     */
    Suit(char abbr, boolean red) {
        this.abbr = abbr;
        this.red = red;
    }

    /**
     * @return whether the suit is red
     */
    public boolean isRed() {
        return red;
    }

    /**
     * Return the string representation of the suit the abbreviation.
     *
     * @return the abbreviation of the suit
     */
    @Override
    public String toString() {
        return String.valueOf(abbr);
    }
}
