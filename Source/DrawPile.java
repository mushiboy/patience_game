import java.util.ArrayList;

public class DrawPile extends Pile {
    private ArrayList<Card> cards;

    /**
     * DrawPile constructor, creates a new Pile of size 24 (cards remaining after
     * dealing).
     */
    public DrawPile() {
        super(24);
        cards = getCards();
    }

    /**
     * Draws a card from the top of the deck and adds it to the top of the drawPile.
     *
     * @param deck the deck of cards to draw from.
     */
    public boolean draw(Deck deck) {
        if (deck.getSize() == 0) {
            if (cards.size() == 0) {
                System.out.println("Deck and draw pile are both empty.");
                return false;
            } else {
                for (Card card : cards) {
                    deck.add(card);
                }
                cards.clear();
                return true;
            }
        } else {
            cards.add(0, deck.takeTop());
            cards.get(0).setShowing(true);
            return true;
        }
    }

    /**
     * Returns a string representation of the DrawPile. Shows up to the top three
     * cards.
     *
     * @return string representing the DrawPile.
     */
    @Override
    public String toString() {
        String temp = "";
        int size = cards.size();
        if (size > 0) {
            temp += cards.get(0);
            if (size > 1) {
                temp += cards.get(1).toString().substring(1);
                if (size > 2) {
                    temp += cards.get(2).toString().substring(1);
                }
            }
        }
        return temp;
    }
}
