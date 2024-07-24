import java.util.Scanner;
public class Main {

private static final int ROW = 3;
private static final int COL = 3;
private static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
        {
            Scanner in = new Scanner(System.in);
            int moveCounter = 0;
            String player = "X";
            int rowCoord = 3; // the initial coordinates are invalid
            int colCoord = 3;
            boolean turnComplete = false;
            boolean condMet = false; // conditions for Win, Loss, Tie met
            clearBoard();
        do {
            do {
                System.out.println("Player " + player + ", enter the row and column coordinates.");
                rowCoord = SafeInput.getRangedInt(in, "ROW", 1, 3) - 1;
                colCoord = SafeInput.getRangedInt(in, "COLUMN", 1, 3) - 1;
                turnComplete = isValidMove(rowCoord, colCoord);
                if (turnComplete == true) {
                    board[rowCoord][colCoord] = player;
                    showBoard();
                    moveCounter++;
                    // here's where you check WLT conditions
                    if (player == "X") player = "Y";
                    else player = "X";
                } else System.out.println("Space taken, try again!");
            } while (!turnComplete);
        }while(!condMet);









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



}
