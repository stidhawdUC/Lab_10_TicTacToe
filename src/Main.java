import java.util.Scanner;
public class Main
{
private static final int ROW = 3;
private static final int COL = 3;
private static String board[][] = new String[ROW][COL];

private static int xWins = 0;
private static int oWins = 0;
private static int tieEnds = 0;


public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        do {
            int moveCounter = 0;
            String player = "X";
            int rowCoord = 3; // the initial coordinates are invalid
            int colCoord = 3;
            boolean turnComplete = false; // used for ending turn
            boolean winMet = false; // conditions for win met
            boolean tieMet = false; // conditions for tie met
            clearBoard();
            do {
                do {
                    System.out.println("Move " + (moveCounter + 1)); // tracking moves
                    System.out.println("Player " + player + ", enter the row and column coordinates.");
                    //initial prompt since it's a two-part question
                    rowCoord = SafeInput.getRangedInt(in, "ROW", 1, 3) - 1;
                    colCoord = SafeInput.getRangedInt(in, "COLUMN", 1, 3) - 1;
                    turnComplete = isValidMove(rowCoord, colCoord); // turn is complete if move is valid
                    if (turnComplete) {
                        board[rowCoord][colCoord] = player;
                        showBoard();
                        moveCounter++;
                        if (moveCounter >= 5) winMet = isWin(player);
                        if (moveCounter == 7 || moveCounter == 9) tieMet = isTie(moveCounter);
                        //any end conditions will skip player-switch
                        if (!winMet && !tieMet) {
                            if (player == "X") player = "O";
                            else player = "X";
                        }
                    } else System.out.println("Space taken, try again!");
                } while (!turnComplete);
            } while (!winMet && !tieMet); // end conditions end the turn and the game
            // this is the post-game
            if (winMet) {
                if (player.equals("X")) xWins++;
                else oWins++;
                System.out.println("Player " + player + " wins!");
            }
            else
            {
                tieEnds++;
                System.out.println("The players tie!");
            }

            playAgain = SafeInput.getYNConfirm(in, "Play again? [Y/N]");
            if(playAgain) {
                System.out.println("OK!");
                winTracker();
            }
        }while(playAgain);
        System.out.println("\nFinal Stats:");
        winTracker();
        System.out.println("Goodbye!");
    }

    private static void clearBoard()
            {
                for(int row = 0; row < ROW; row++)
                {
                    for(int col = 0; col < COL; col++)
                    {
                        board[row][col] = " "; // this makes the cell a space, empty.
                    }
                }
            }
    private static boolean isValidMove(int rowCheck, int colCheck)
            {
                boolean retVal = false;
                if(board[rowCheck][colCheck].equals(" ")) retVal = true;
                return retVal;
            }
    private static void showBoard()
            {
                System.out.println("");
                for(int row = 0; row < ROW; row++)
                {
                    System.out.print("|");
                    for(int col = 0; col < COL; col++)
                    {
                        System.out.printf("%1S|", board[row][col]);
                    }
                    System.out.println("");
                    // was going to have System.out.print("\n---------------\n"); but didn't like it
                }
                System.out.println("");
            }
    private static boolean isWin(String player)
            {
                if(isColWin(player) || isRowWin(player) || isDiagWin(player)) return true;
                else return false;  // "else" technically isn't necessary
                                    // but helps me read it
                                    // will intelliJ suggested simplifications work? i won't use them
            }
            private static boolean isColWin(String player)
            {
                for (int col = 0; col < COL; col++) {
                    if (board[0][col].equals(player) && board[1][col].equals(player)
                            && board[2][col].equals(player))
                    {
                        return true;
                    }
                }
                return false;
            }
            private static boolean isRowWin(String player)
            {
                for(int row=0; row < ROW; row ++)
                {
                    if(board[row][0].equals(player) && board[row][1].equals(player)
                            && board[row][2].equals(player))
                    {
                        return true;
                    }
                }
                return false;
            }
            private static boolean isDiagWin(String player)
            {
                if(     (board[0][0].equals(player) && board[1][1].equals(player)
                        && board[2][2].equals(player))
                    || (board[0][2].equals(player) && board[1][1].equals(player)
                        && board[2][0].equals(player)))
                {
                    return true;
                }
                return false;
            }
    private static boolean isTie(int moveCounter)
    {   // checking to make sure all win vectors are closed.
        // any open win vectors will return false immediately, with one exception.
        for(int row = 0; row < ROW; row++) // checking row win vectors
        {
            if(board[row][0].equals("X") || board[row][1].equals("X")
                    || board[row][2].equals("X"))
            {
                if(board[row][0].equals("O") || board[row][1].equals("O")
                        || board[row][2].equals("O"));
                // if true, vector is closed and we keep checking.
                // could have inverted this with &&s and !s, but I confused myself that way.
                else
                        // below is the one exception. at move 7, two open with one X isn't an open vector.
                        // even with a thrown / tactless turn from O, that can't be won.
                        // this ensures that, at 7 moves, an [XOX/-X-/OXO] game will tie,
                        // but a [XOX/XOO/--X] game will not.
                {
                    if((board[row][0].equals(" ") && board[row][1].equals(" "))
                            || (board[row][1].equals(" ") && board[row][2].equals(" "))
                            || (board[row][0].equals(" ") && board[row][2].equals(" ")));
                    else return false;
                }
            }
        }
        for(int col = 0; col < COL; col++) // checking col win vectors
        {
            if(board[0][col].equals("X") || board[1][col].equals("X")
                    || board[2][col].equals("X"))
            {
                if(board[0][col].equals("O") || board[1][col].equals("O")
                        || board[2][col].equals("O"));
                else
                {
                    if((board[0][col].equals(" ") && board[1][col].equals(" "))
                            || (board[1][col].equals(" ") && board[2][col].equals(" "))
                            || (board[0][col].equals(" ") && board[2][col].equals(" ")));
                    else return false;
                }
            }
        }
        // diag check 1 vvv
        if(board[0][0].equals("X") || board[1][1].equals("X")
                || board[2][2].equals("X"))
        {
            if(board[0][0].equals("O") || board[1][1].equals("O")
                    || board[2][2].equals("O"));
            else return false;
        }
        //diag check 2 vvv
        if(board[0][2].equals("X") || board[1][1].equals("X")
                || board[2][0].equals("X"))
        {
            if(board[0][2].equals("O") || board[1][1].equals("O")
                    || board[2][0].equals("O"));
            else return false;
        }
        return true;
    }
            private static void winTracker()
            {
                String xIntro = "Player X Wins:";
                String oIntro = "Player O Wins:";
                String tiesIntro = "Player Ties:";

                System.out.printf("%-15s %2d\n", xIntro, xWins);
                System.out.printf("%-15s %2d\n", oIntro, oWins);
                System.out.printf("%-15s %2d\n", tiesIntro, tieEnds);
                System.out.println("");
            }
}
