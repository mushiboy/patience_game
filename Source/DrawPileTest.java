
public class DrawPileTest {
    // Create a new deck and draw three Cards into the Draw Pile, printing the Draw
    // Pile each time.
    public static void main(String[] args) {
        Deck deck = new Deck();
        DrawPile draw = new DrawPile();
        draw.draw(deck);
        System.out.println(draw);
        draw.draw(deck);
        System.out.println(draw);
        draw.draw(deck);
        System.out.println(draw);
    }
}
