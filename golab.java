import java.util.Scanner;

public class golab {

    static String[][] goBoard = new String[9][9];
    static final String EMPTY = "+";
    static final String Black = "o";
    static final String White = "x";

    static int blackScore = 0;
    static int whiteScore = 0;

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        boolean player1 = true;
        boolean playing = true;
        int moveX, moveY;

        System.out.print("**Welcome to the game of Go!** \nPlease select from the options below: \n");
        System.out.println("Type 'P' to Play");
        System.out.println("Type 'Q' to Quit");

        String input = scn.nextLine().toUpperCase();

        switch (input) {
            case "P":
                System.out.println("Good Luck!");
                initializeBoard();  // Moved here so board only sets up when playing
                break;
            case "Q":
                System.out.println("Exiting... :(");
                playing = false;
                break;
            default:
                System.out.println("Not an option.");
                playing = false;
        }

        while (playing) {

            printBoard();

            System.out.println("Enter x coord (0-8):");
            if (!scn.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scn.next();
                continue;
            }
            moveX = scn.nextInt();

            System.out.println("Enter y coord (0-8):");
            if (!scn.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scn.next();
                continue;
            }
            moveY = scn.nextInt();
            scn.nextLine();

            // Check bounds
            if (moveX < 0 || moveX >= 9 || moveY < 0 || moveY >= 9) {
                System.out.println("Out of bounds! Try again.");
                continue;
            }

            // Check if spot is open
            if (!goBoard[moveY][moveX].equals(EMPTY)) {
                System.out.println("That spot is already taken! Try again.");
                continue;
            }

            // Placing the piece
            String currentPlayer = player1 ? Black : White;
            goBoard[moveY][moveX] = currentPlayer;

            // Check for captures
            checkCapture(moveX, moveY, currentPlayer);

            // Calculate and print the score
            calculateScore();
            printScore();

            // Switch players
            player1 = !player1;
        }

        scn.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < goBoard.length; i++) {
            for (int j = 0; j < goBoard[i].length; j++) {
                goBoard[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for (int i = 0; i < goBoard.length; i++) {
            System.out.print((i) + " ");
            for (int j = 0; j < goBoard[i].length; j++) {
                System.out.print(goBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printScore() {
        System.out.println("Score:");
        System.out.println("Black (o): " + blackScore);
        System.out.println("White (x): " + whiteScore);
    }

    private static void calculateScore() {
        blackScore = 0;
        whiteScore = 0;

        for (int i = 0; i < goBoard.length; i++) {
            for (int j = 0; j < goBoard[i].length; j++) {
                if (goBoard[i][j].equals(Black)) {
                    blackScore++;
                } else if (goBoard[i][j].equals(White)) {
                    whiteScore++;
                }
            }
        }
    }

    private static void checkCapture(int x, int y, String currentPlayer) {
        String opponent = currentPlayer.equals(Black) ? White : Black;
        checkGroupLiberties(x + 1, y, opponent);
        checkGroupLiberties(x - 1, y, opponent);
        checkGroupLiberties(x, y + 1, opponent);
        checkGroupLiberties(x, y - 1, opponent);
    }

    private static void checkGroupLiberties(int x, int y, String player) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9 || !goBoard[y][x].equals(player)) {
            return;
        }

        boolean[][] visited = new boolean[9][9];
        if (!hasLiberties(x, y, player, visited)) {
            removeGroup(x, y, player);
        }
    }

    private static boolean hasLiberties(int x, int y, String player, boolean[][] visited) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9 || visited[y][x]) {
            return false;
        }

        if (goBoard[y][x].equals(EMPTY)) {
            return true;
        }

        if (!goBoard[y][x].equals(player)) {
            return false;
        }

        visited[y][x] = true;

        return hasLiberties(x + 1, y, player, visited) ||
               hasLiberties(x - 1, y, player, visited) ||
               hasLiberties(x, y + 1, player, visited) ||
               hasLiberties(x, y - 1, player, visited);
    }

    private static void removeGroup(int x, int y, String player) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9 || !goBoard[y][x].equals(player)) {
            return;
        }

        goBoard[y][x] = EMPTY;

        removeGroup(x + 1, y, player);
        removeGroup(x - 1, y, player);
        removeGroup(x, y + 1, player);
        removeGroup(x, y - 1, player);
    }
}