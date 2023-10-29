/**
 * Value.java - Enum class for the different values of playing card.
 */

/**
 * Values of playing card.
 */
public enum Value {
    ACE('A'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'),
    JACK('J'), QUEEN('Q'), KING('K');

    private char abbr; // abbreviation of the value.

    /**
     * enum constructor for value
     *
     * @param abbr the abbreviation of the value
     */
    Value(char abbr) {
        this.abbr = abbr;
    }

    /**
     * Returns the string representation of the value.
     *
     * @return the abbreviation of the value as a string
     */
    @Override
    public String toString() {
        return String.valueOf(abbr);
    }
}
