import java.util.ArrayList;

public class Lane extends Pile {
    ArrayList<Card> cards;

    /**
     * Lane Constructor
     */
    public Lane() {
        super(13);
        cards = super.getCards();
    }

    /**
     * Removes the first n cards and returns them.
     *
     * @param n the number of cards to take
     * @return an ArrayList containing the cards taken.
     */
    public ArrayList<Card> takeFirst(int n) {
        ArrayList<Card> temp = new ArrayList<>(cards.subList(0, n));
        cards.subList(0, n).clear();
        if (getSize() != 0)
            cards.get(0).setShowing(true);
        return temp;
    }

    /**
     * Returns an ArrayList of the first n cards in the Lane without removing them.
     *
     * @param n the number of cards to return
     * @return an ArrayList containing the cards.
     */
    public ArrayList<Card> viewFirst(int n) {
        ArrayList<Card> temp = new ArrayList<>(cards.subList(0, n));
        return temp;
    }

    /**
     * Add multiple cards to the Lane.
     *
     * @param cards the cards to be added.
     */
    public void add(ArrayList<Card> cards) {
        this.cards.addAll(0, cards);
    }
}
