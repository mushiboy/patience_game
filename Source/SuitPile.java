import java.util.ArrayList;

/**
 * SuitPile.java - Models a suit pile in patience.
 */

public class SuitPile extends Pile {
    private ArrayList<Card> cards;
    private Suit suit; // Which suit should go into this pile

    /**
     * SuitPile Constructor, creates a new ArrayList of size 13 for cards to be
     * added to.
     *
     * @param suit the suit which will go in the pile.
     */
    public SuitPile(Suit suit) {
        super(13);
        cards = getCards();
        this.suit = suit;
    }

    /**
     * Returns a string representation of the Suit Pile, showing either an empty
     * pile or the top card.
     *
     * @return string representing the Suit Pile
     */
    @Override
    public String toString() {
        if (cards.size() > 0)
            return cards.get(0).toString();
        else
            return "    ";
    }

    /**
     * Return the suit of the pile
     *
     * @return the suit of the pile
     */
    public Suit getSuit() {
        return suit;
    }
}
