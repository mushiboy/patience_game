/**
 * Table.java - Models the patience table, with the Lanes, deck, drawPile, and
 * suit piles.
 * 
 */

public class Table {
    Lane[] lanes;
    Deck deck;
    DrawPile drawPile;
    SuitPile[] suitPiles;
    Pile[] locations; // Lanes, SuitPiles, and the DrawPile

    /**
     * Table Constructor - Creates a new table for the beginning of a game of
     * Patience.
     */
    public Table() {
        lanes = new Lane[7];
        deck = new Deck();
        deck.shuffle();
        drawPile = new DrawPile();
        suitPiles = new SuitPile[4];
        locations = new Pile[12];

        /*
         * Deals the cards differently to normally, going lane by lane instead of row by
         * row. As the deck is shuffled this doesn't make any difference.
         */
        for (int i = 0; i < 7; i++) {
            lanes[i] = new Lane();
            for (int j = 0; j <= i; j++) {
                lanes[i].add(deck.takeTop());
            }
            lanes[i].viewTop().setShowing(true);
            locations[i] = lanes[i];
        }

        for (int i = 0; i < 4; i++) {
            suitPiles[i] = new SuitPile(Suit.values()[i]);
            locations[i + 7] = suitPiles[i];
        }
        locations[11] = drawPile;
    }

    /**
     * Return locations
     *
     * @return locations
     */
    public Pile[] getLocations() {
        return locations;
    }

    /**
     * Print the table
     */
    public void printTable() {
        printLanes();
        System.out.println();
        printSuits();
        System.out.println();
        System.out.println("Deck:" + deck + "  P: " + drawPile);
    }

    /**
     * Print the 7 Lanes
     */
    private void printLanes() {
        int max = 0;
        for (Lane lane : lanes) {
            if (lane.getSize() > max)
                max = lane.getSize();
        }
        System.out.println(" 01  02  03  04  05  06  07");
        for (int i = 0; i < max; i++) {
            for (Lane lane : lanes) {
                // If the lane has i cards print them. If not print a blank space.
                if (lane.getSize() > i)
                    System.out.print(lane.get(lane.getSize() - 1 - i));
                else
                    System.out.print("    ");
            }
            System.out.println();
        }
    }

    /**
     * Print the 4 Suit Piles
     */
    private void printSuits() {
        for (int i = 0; i < 4; i++) {
            System.out.print(Suit.values()[i] + ": " + suitPiles[i]);
        }
    }

    /**
     * Move one card from location from to location to
     *
     * @param from the origin of the card
     * @param to   the destination of the card
     */
    public void move(int from, int to) {
        locations[to].add(locations[from].takeTop());

        // If there are cards left at from, make sure the top is showing.
        if (locations[from].getSize() != 0)
            locations[from].viewTop().setShowing(true);
    }

    /**
     * Move multiple cards from location from to location to
     *
     * @param from the origin of the cards
     * @param to   the destination of the cards
     * @param num  the number of cards to move
     */
    public void move(int from, int to, int num) {
        ((Lane) locations[to]).add(((Lane) locations[from]).takeFirst(num));

        // If there are cards left at from, make sure the top is showing.
        if (locations[from].getSize() != 0)
            locations[from].viewTop().setShowing(true);
    }

    /**
     * Draw a new card into the Draw Pile
     *
     * @return whether the draw was successful.
     */
    public boolean draw() {
        return drawPile.draw(deck);
    }

    /**
     * Return how many cards are in the Suit Piles
     *
     * @return the number of cards in the Suit Piles
     */
    public int getCardsInSuitPiles() {
        int cards = 0;
        for (SuitPile suitPile : suitPiles)
            cards += suitPile.getSize();
        return cards;
    }

}
