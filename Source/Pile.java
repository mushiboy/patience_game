import java.util.ArrayList;

/**
 * Pile.java - An abstract class to represent a pile of cards.
 */

/**
 * A {@code Pile} object represents a pile of cards.
 */
public abstract class Pile {
    ArrayList<Card> cards;

    /**
     * Pile constructor
     */
    Pile() {
        cards = new ArrayList<>();
    }

    /**
     * Alternative constructor
     *
     * @param initialSize the initial size of the ArrayList
     */
    Pile(int initialSize) {
        cards = new ArrayList<>(initialSize);
    }

    /**
     * Return the cards in the pile
     *
     * @return an ArrayList of cards in the Pile
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Returns the card at index index
     *
     * @param index the index of the card to be returned
     * @return the card at index index
     */
    public Card get(int index) {
        return cards.get(index);
    }

    /**
     * Returns the size of the pile
     *
     * @return the size of the pile
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * Returns the top card on the pile.
     *
     * @return the top card on the pile.
     */
    public Card viewTop() {
        return cards.get(0);
    }

    /**
     * Returns the top card on the pile and removes it.
     *
     * @return the top card on the pile.
     */
    public Card takeTop() {
        Card temp = cards.get(0);
        cards.remove(0);
        return temp;
    }

    /**
     * Adds a card to the top of the Suit Pile.
     *
     * @param card the card to add to the pile
     */
    public void add(Card card) {
        cards.add(0, card);
    }
}
