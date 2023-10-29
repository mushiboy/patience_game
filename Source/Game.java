import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private Table table;
    private Pile[] locations;
    private int score;
    private int moves;

    /**
     * Game Constructor. Create a new table and set score and moves to 0.
     */
    public Game() {
        table = new Table();
        locations = table.getLocations();
        score = 0;
        moves = 0;
    }

    /**
     * Check whether moving {@code num} cards from {@code from} to {@code to} was
     * successful.
     *
     * @param from the origin of the card(s)
     * @param to   the destination of the card(s)
     * @param num  the number of cards to move
     * @return whether the move was a success
     */
    private boolean checkMove(int from, int to, int num) {
        if (num < 1) {
            System.out.println("Number of cards being moved must be at least 1.");
            return false;
        }
        if (locations[from].getSize() == 0) {
            System.out.println("No cards to move.");
            return false;
        }
        if (to == 11) {
            System.out.println("Can't move cards to the draw pile.");
            return false;
        }
        if (from > 6 && num > 1) {
            System.out.println("Can only move one card at a time from the draw pile or suit piles.");
            return false;
        }
        if (from > 6 && to > 6 && from != 11) {
            System.out.println("Can't move from one suit pile to another.");
            return false;
        }
        if (to < 7) // Moving to a lane
        {
            if (num > 1)
                return checkMultipleMoveToLane(from, to, num, true);
            else
                return checkSingleMoveToLane(from, to);
        } else // Moving to a suit
            return checkMoveToSuit(from, to);
    }

    /**
     * Check whether moving a single card from {@code from} to lane {@code to} was
     * successful.
     *
     * @param from the origin of the card
     * @param to   the destination of the card.
     * @return whether the move was successful.
     */
    private boolean checkSingleMoveToLane(int from, int to) {
        if (locations[to].getSize() == 0) // Only a king can move to an empty lane.
        {
            if (locations[from].viewTop().getValue() == Value.KING) {
                table.move(from, to);
                if (from < 7) {
                    score += 5;
                }
                moves++;
                return true;
            } else {
                System.out.println("Can only move a King to an empty lane.");
                return false;
            }
        }
        // Otherwise moving card must be 1 lower and opposite colour to destination
        // card.
        if (locations[from].viewTop().isRed() != locations[to].viewTop().isRed()
                && locations[from].viewTop().ordinal() == locations[to].viewTop().ordinal() - 1) {
            table.move(from, to);
            score += 5;
            moves++;
            return true;
        } else
            return false;
    }

    /**
     * Check whether moving {@code num} cards from {@code from} to lane {@code to}
     *
     * @param from        the origin of the card
     * @param to          the destination of the card.
     * @param num         the number of cards to move.
     * @param printErrors whether to print errors if the move is unsuccessful.
     * @return whether the move was successful.
     */
    private boolean checkMultipleMoveToLane(int from, int to, int num, boolean printErrors) {
        // Make sure enough cards are visible to try to remove.
        int visibleCards = 0;
        for (Card card : locations[from].getCards())
            if (card.isShowing())
                visibleCards++;
        if (num > visibleCards) {
            System.out.println("Not enough cards visible.");
            return false;
        }

        Card fromCard = ((Lane) locations[from]).viewFirst(num).get(num - 1); // The highest visible card.

        // Same logic as for 1 card
        if (locations[to].getSize() == 0) {
            if (fromCard.getValue() == Value.KING) {
                table.move(from, to, num);
                score += 5;
                moves++;
                return true;
            } else {
                if (printErrors)
                    System.out.println("Can only move a King to an empty lane.");
                return false;
            }
        }
        if (fromCard.isRed() != locations[to].viewTop().isRed()
                && fromCard.ordinal() == locations[to].viewTop().ordinal() - 1) {
            table.move(from, to, num);
            score += 5;
            moves++;
            return true;
        } else {
            if (printErrors)
                System.out.println("Move not valid.");
            return false;
        }
    }

    /**
     * Try to move as many cards as possible from {@code from} to {@code to}
     *
     * @param from the origin of the card(s)
     * @param to   the destination of the card(s)
     * @return whether any move was possible.
     */
    private boolean checkMoveMostToLane(int from, int to) {
        int visibleCards = 0;
        for (Card card : locations[from].getCards())
            if (card.isShowing())
                visibleCards++;
        if (visibleCards == 0) {
            System.out.println("No cards to move.");
            return false;
        }
        boolean success = false;
        // Keep trying as many moves as possible until one succeeds or we run out
        // visible cards.
        do {
            success = checkMultipleMoveToLane(from, to, visibleCards--, false);
        } while (!success && visibleCards > 0);
        if (success)
            return true;
        System.out.println("Move not possible.");
        return false;
    }

    /**
     * Check whether moving from {@code from} to Suit Pile {@to} was successful.
     *
     * @param from the origin of the card.
     * @param to   the destination of the card.
     * @return whether the move was successful.
     */
    private boolean checkMoveToSuit(int from, int to) {
        SuitPile toPile = (SuitPile) locations[to];
        Card fromCard = locations[from].viewTop();
        if (fromCard.getSuit() == toPile.getSuit()) // Must be the correct suit.
        {
            // Must be an ACE or the next card up.
            if ((toPile.getSize() == 0 && fromCard.getValue() == Value.ACE)
                    || fromCard.ordinal() == toPile.viewTop().ordinal() + 1) {
                table.move(from, to);
                if (from == 11)
                    score += 10;
                else
                    score += 20;
                moves++;
                return true;
            }
            return false;
        } else {
            System.out.println("Move not valid.");
            return false;
        }

    }

    /**
     * Process the user input and, (if valid) attempt a move
     *
     * @param input the user input String
     * @return whether a move was successful.
     */
    private boolean processInput(String input) {
        // Create a regex which matches the valid inputs.
        Pattern movePattern = Pattern.compile("([1-7]|[hdcsp])([1-7]|[hdcs])(\\d)*");
        Matcher moveMatcher = movePattern.matcher(input);

        // If the input is a valid move.
        if (moveMatcher.matches()) {
            // Convert the string reference to the pile to an integer with its index in
            // locations.
            String from = moveMatcher.group(1);
            String to = moveMatcher.group(2);
            // If num was not specified it is set to 1.
            int num = 1;
            if (moveMatcher.group(3) != null)
                num = Integer.parseInt(moveMatcher.group(3));

            int fromN = switch (from) {
                case "h" -> 7;
                case "d" -> 8;
                case "c" -> 9;
                case "s" -> 10;
                case "p" -> 11;
                default -> Integer.parseInt(from) - 1;
            };

            int toN = switch (to) {
                case "h" -> 7;
                case "d" -> 8;
                case "c" -> 9;
                case "s" -> 10;
                default -> Integer.parseInt(to) - 1;
            };

            // If two lane numbers and no num was given try to move as many cards as
            // possible.
            if (fromN < 7 && toN < 7 && moveMatcher.group(3) == null)
                return checkMoveMostToLane(fromN, toN);

            // Otherwise try to move num cards.
            return checkMove(fromN, toN, num);

        } else if (input.equals("d")) {
            return table.draw();
        } else if (input.equals("q")) {
            System.out.println("Goodbye!");
            System.out.println("Final Score: " + score + "\nTotal Moves: " + moves);
            return false;
        } else {
            System.out.println("Input not valid.");
            return false;
        }
    }

    /**
     * Print the scores and the table.
     */
    private void printGame() {
        System.out.println("Score: " + score + " Moves: " + moves);
        table.printTable();
    }

    /**
     * Print the welcome and instructions.
     */
    public void printWelcome() {
        System.out.println("Patience.\n");
        System.out.println("Commands can be entered in the following form:\nEITHER:");
        System.out.println("\t<label1><label2> = move one card from <label1> to <label2> E.g. \"P2\" or \"2D\"");
        System.out.println("\t<label1><label2><number> = move <number> cards from <label1> to <label2> E.g. \"413\"");
        System.out.println("OR:");
        System.out.println(
                "\t<label1><label2> = move card(s) from <label1> to <label2> detecting the number of cards that need to move so as to make a legal move if at all possible. E.g. \"21\" or \"63\"");
        System.out.println("Commands are accepted in either uppor or lower case.\n");

    }

    /**
     * Play the game.
     */
    public void play() {
        Scanner in = new Scanner(System.in);
        String input;
        printGame();
        // Process user input until they win or quit.
        do {
            System.out.print("Please enter a command:");
            input = in.nextLine().toLowerCase();
            if (processInput(input))
                printGame();
        } while (!input.equals("q") && table.getCardsInSuitPiles() < 52);
        if (table.getCardsInSuitPiles() >= 52) {
            table.printTable();
            System.out.println("Congratulations, you win!");
            System.out.println("Final Score: " + score + "\nTotal Moves: " + moves);
        }
        in.close();
    }
}
