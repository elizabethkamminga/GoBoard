import java.util.Scanner;

public class golab {
    static String[][] goBoard = new String[9][9];
    public static void main(String[] args) throws Exception {

        Scanner scn = new Scanner(System.in);
        boolean player1 = true;
        boolean playing = true;
        boolean flipper = true;
        int moveX, moveY;
        
        System.out.print("Welcome to Go! \n Please eneter one of the following options:\n");
        System.out.println(" 1: Play with a friend");
        System.out.println(" 2: Play against computer");
        System.out.println(" 3: Change dimension");
        System.out.println( "4: Quit");

        String input = scn.nextLine();

        while(true){
            goBoard[1][1] = "-o";
            goBoard[2][2] = "-*";

            System.out.println(" 0 1 2 3 4 5 6 7 8");
            for(int i = 0; i < goBoard[0].length; i++) {
                System.out.print((i+1) +" ");
                for(int j = 0; j < goBoard.length; j++) {
                    if (goBoard[i][j] == null) {
                        if (j==0){
                            System.out.print("|");
                        } 
                        else{
                            System.out.print("-|");
                        }
                    } 
                    else{
                        System.out.print(goBoard[i][j]);    
                    }
                }
                System.out.println();
            }
            System.out.println("Please enter x coord:");
            moveX = scn.nextInt();
            System.out.println("Please enter y coord:");
            moveY = scn.nextInt();
        
            System.out.println("The piece is: " + goBoard[moveY][moveX]);
        
            goBoard[moveY][moveX] = (player1) ? "o" : "*";
        }
        

    }

}

