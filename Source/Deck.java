import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck.java - Models a deck of cards. Inherits from Pile.
 */

public class Deck extends Pile {
    private ArrayList<Card> cards; // Cards in the deck
    private static final int DECKSIZE = Value.values().length * Suit.values().length; // 13x4 = 52

    /**
     * Deck constructor. Creates a new ArrayList of Cards and fills it with the 52
     * different cards.
     */
    public Deck() {
        super(DECKSIZE);
        this.cards = super.getCards();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(0, new Card(value, suit));
            }
        }
    }

    /**
     * Shuffles the cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
     * Returns a string representation of the deck, showing if there are any cards
     * left in it.
     *
     * @return a string representation of the deck.
     */
    @Override
    public String toString() {
        if (cards.size() > 0)
            return "[  ]";
        else
            return "    ";
    }
}
