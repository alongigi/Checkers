import java.util.Scanner;


public class EnglishCheckers {

    // Global constants
    public static final int RED   = 1;
    public static final int BLUE  = -1;
    public static final int EMPTY = 0;

    public static final int SIZE  = 8;

    // You can ignore these constants
    public static final int MARK  = 3;
    public static EnglishCheckersGUI grid;

    public static Scanner getPlayerFullMoveScanner = null;
    public static Scanner getStrategyScanner = null;

    public static final int RANDOM			= 1;
    public static final int DEFENSIVE		= 2;
    public static final int SIDES				= 3;
    public static final int CUSTOM			= 4;


    public static void main(String[] args) {

        // ******** Don't delete *********
        // CREATE THE GRAPHICAL GRID
        grid = new EnglishCheckersGUI(SIZE);
        // *******************************


        //showBoard(example);
        //printMatrix(example);

        interactivePlay();
        //twoPlayers();


		/* ******** Don't delete ********* */
        if (getPlayerFullMoveScanner != null){
            getPlayerFullMoveScanner.close();
        }
        if (getStrategyScanner != null){
            getStrategyScanner.close();
        }
		/* ******************************* */

    }


    public static int[][] createBoard() {
        int[][] board = {{1,0,1,0,1,0,1,0},{0,1,0,1,0,1,0,1},{1,0,1,0,1,0,1,0},
                {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
                {0,-1,0,-1,0,-1,0,-1},{-1,0,-1,0,-1,0,-1,0},{0,-1,0,-1,0,-1,0,-1}};

        return board;
    }


    public static int[][] playerDiscs(int[][] board, int player) {
        int counter = 0;
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j]==player||board[i][j]==player+1||board[i][j]==player-1){
                    counter++;
                }
            }
        }

        int[][] positions = new int[counter][2];
        int t = 0;
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if(board[i][j]==player||board[i][j]==player+1||board[i][j]==player-1){
                    board[t][0]=i;
                    board[t][1]=j;
                    t++;
                }
            }
        }

        return positions;
    }


    public static boolean isBasicMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
        boolean ans = false;

        if(board[fromRow][fromCol]!=player||board[fromRow][fromCol]!=player+1||board[fromRow][fromCol]!=player-1)
            return ans;
        else if(board[toCol][toRow]!=0)
            return ans;
        else if((toCol!=fromCol+1&&toCol!=fromCol-1)||(toRow!=fromRow+1&&toRow!=fromRow-1))
            return ans;
        else if(player>0&& toRow<fromRow || player<0&&toRow>fromRow)
            return ans;
        else return true;
    }


    public static int[][] getAllBasicMoves(int[][] board, int player) {
        int[][] discs = playerDiscs(board,player);
        int counter = 0;
        for (int i = 0; i<discs[0].length;i++){
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]+1,discs[i][0]+1))
                counter++;
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]+1,discs[i][0]-1))
                counter++;
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]-1,discs[i][0]+1))
                counter++;
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]-1,discs[i][0]-1))
                counter++;
        }

        int[][] moves = new int[4][counter];
        int column = 0;
        for (int i = 0; i<discs[0].length;i++){
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]+1,discs[i][0]+1))
                moves[column][0]=discs[i][1];
                moves[column][1]=discs[i][0];
                moves[column][2]=discs[i][1]+1;
                moves[column][3]=discs[i][0]+1;
                column++;
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]+1,discs[i][0]-1))
                moves[column][0]=discs[i][1];
                moves[column][1]=discs[i][0];
                moves[column][2]=discs[i][1]+1;
                moves[column][3]=discs[i][0]-1;
                column++;
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]-1,discs[i][0]+1))
                moves[column][0]=discs[i][1];
                moves[column][1]=discs[i][0];
                moves[column][2]=discs[i][1]-1;
                moves[column][3]=discs[i][0]+1;
                column++;
            if(isBasicMoveValid(board,player,discs[i][1],discs[i][0],discs[i][1]-1,discs[i][0]-1))
                moves[column][0]=discs[i][1];
                moves[column][1]=discs[i][0];
                moves[column][2]=discs[i][1]-1;
                moves[column][3]=discs[i][0]-1;
                column++;
        }

        //Add your code here

        return moves;
    }


    public static boolean isBasicJumpValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
        boolean ans = false;

        //Add your code here

        return ans;
    }


    public static int [][] getRestrictedBasicJumps(int[][] board, int player, int row, int col) {
        int[][] moves = null;

        //Add your code here

        return moves;
    }


    public static int[][] getAllBasicJumps(int[][] board, int player) {
        int[][] moves = null;

        //Add your code here

        return moves;
    }


    public static boolean canJump(int[][] board, int player) {
        boolean ans = false;

        //Add your code here

        return ans;
    }


    public static boolean isMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
        boolean ans = false;

        //Add your code here

        return ans;
    }


    public static boolean hasValidMoves(int[][] board, int player) {
        boolean ans = false;

        //Add your code here

        return ans;
    }


    public static int[][] playMove(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {

        //Add your code here

        return board;
    }


    public static boolean gameOver(int[][] board, int player) {
        boolean ans = false;

        //Add your code here

        return ans;
    }


    public static int findTheLeader(int[][] board) {
        int ans = 0;

        //Add your code here

        return ans;
    }


    public static int[][] randomPlayer(int[][] board, int player) {

        //Add your code here

        return board;
    }


    public static int[][] defensivePlayer(int[][] board, int player) {

        //Add your code here

        return board;
    }

    public static int[][] sidesPlayer(int[][] board, int player) {

        //Add your code here

        return board;
    }












    //******************************************************************************//

    /* ---------------------------------------------------------- *
     * Play an interactive game between the computer and you      *
     * ---------------------------------------------------------- */
    public static void interactivePlay() {
        int[][] board = createBoard();
        showBoard(board);

        System.out.println("Welcome to the interactive Checkers Game !");

        int strategy = getStrategyChoice();
        System.out.println("You are the first player (RED discs)");

        boolean oppGameOver = false;
        while (!gameOver(board, RED) && !oppGameOver) {
            board = getPlayerFullMove(board, RED);

            oppGameOver = gameOver(board, BLUE);
            if (!oppGameOver) {
                EnglishCheckersGUI.sleep(200);

                board = getStrategyFullMove(board, BLUE, strategy);
            }
        }

        int winner = 0;
        if (playerDiscs(board, RED).length == 0  |  playerDiscs(board, BLUE).length == 0){
            winner = findTheLeader(board);
        }

        if (winner == RED) {
            System.out.println();
            System.out.println("\t *************************");
            System.out.println("\t * You are the winner !! *");
            System.out.println("\t *************************");
        }
        else if (winner == BLUE) {
            System.out.println("\n======= You lost :( =======");
        }
        else
            System.out.println("\n======= DRAW =======");
    }


    /* --------------------------------------------------------- *
     * A game between two players                                *
     * --------------------------------------------------------- */
    public static void twoPlayers() {
        int[][] board = createBoard();
        showBoard(board);

        System.out.println("Welcome to the 2-player Checkers Game !");

        boolean oppGameOver = false;
        while (!gameOver(board, RED)  &  !oppGameOver) {
            System.out.println("\nRED's turn");
            board = getPlayerFullMove(board, RED);

            oppGameOver = gameOver(board, BLUE);
            if (!oppGameOver) {
                System.out.println("\nBLUE's turn");
                board = getPlayerFullMove(board, BLUE);
            }
        }

        int winner = 0;
        if (playerDiscs(board, RED).length == 0  |  playerDiscs(board, BLUE).length == 0)
            winner = findTheLeader(board);

        System.out.println();
        System.out.println("\t ************************************");
        if (winner == RED)
            System.out.println("\t * The red player is the winner !!  *");
        else if (winner == BLUE)
            System.out.println("\t * The blue player is the winner !! *");
        else
            System.out.println("\t * DRAW !! *");
        System.out.println("\t ************************************");
    }


    /* --------------------------------------------------------- *
     * Get a complete (possibly a sequence of jumps) move        *
     * from a human player.                                      *
     * --------------------------------------------------------- */
    public static int[][] getPlayerFullMove(int[][] board, int player) {
        // Get first move/jump
        int fromRow = -1, fromCol = -1, toRow = -1, toCol = -1;
        boolean jumpingMove = canJump(board, player);
        boolean badMove   = true;
        getPlayerFullMoveScanner = new Scanner(System.in);//I've modified it
        while (badMove) {
            if (player == 1){
                System.out.println("Red, Please play:");
            } else {
                System.out.println("Blue, Please play:");
            }

            fromRow = getPlayerFullMoveScanner.nextInt();
            fromCol = getPlayerFullMoveScanner.nextInt();

            int[][] moves = jumpingMove ? getAllBasicJumps(board, player) : getAllBasicMoves(board, player);
            markPossibleMoves(board, moves, fromRow, fromCol, MARK);
            toRow   = getPlayerFullMoveScanner.nextInt();
            toCol   = getPlayerFullMoveScanner.nextInt();
            markPossibleMoves(board, moves, fromRow, fromCol, EMPTY);

            badMove = !isMoveValid(board, player, fromRow, fromCol, toRow, toCol);
            if (badMove)
                System.out.println("\nThis is an illegal move");
        }

        // Apply move/jump
        board = playMove(board, player, fromRow, fromCol, toRow, toCol);
        showBoard(board);

        // Get extra jumps
        if (jumpingMove) {
            boolean longMove = (getRestrictedBasicJumps(board, player, toRow, toCol).length > 0);
            while (longMove) {
                fromRow = toRow;
                fromCol = toCol;

                int[][] moves = getRestrictedBasicJumps(board, player, fromRow, fromCol);

                boolean badExtraMove = true;
                while (badExtraMove) {
                    markPossibleMoves(board, moves, fromRow, fromCol, MARK);
                    System.out.println("Continue jump:");
                    toRow = getPlayerFullMoveScanner.nextInt();
                    toCol = getPlayerFullMoveScanner.nextInt();
                    markPossibleMoves(board, moves, fromRow, fromCol, EMPTY);

                    badExtraMove = !isMoveValid(board, player, fromRow, fromCol, toRow, toCol);
                    if (badExtraMove)
                        System.out.println("\nThis is an illegal jump destination :(");
                }

                // Apply extra jump
                board = playMove(board, player, fromRow, fromCol, toRow, toCol);
                showBoard(board);

                longMove = (getRestrictedBasicJumps(board, player, toRow, toCol).length > 0);
            }
        }
        return board;
    }


    /* --------------------------------------------------------- *
     * Get a complete (possibly a sequence of jumps) move        *
     * from a strategy.                                          *
     * --------------------------------------------------------- */
    public static int[][] getStrategyFullMove(int[][] board, int player, int strategy) {
        if (strategy == RANDOM)
            board = randomPlayer(board, player);
        else if (strategy == DEFENSIVE)
            board = defensivePlayer(board, player);
        else if (strategy == SIDES)
            board = sidesPlayer(board, player);

        showBoard(board);
        return board;
    }


    /* --------------------------------------------------------- *
     * Get a strategy choice before the game.                    *
     * --------------------------------------------------------- */
    public static int getStrategyChoice() {
        int strategy = -1;
        getStrategyScanner = new Scanner(System.in);
        System.out.println("Choose the strategy of your opponent:" +
                "\n\t(" + RANDOM + ") - Random player" +
                "\n\t(" + DEFENSIVE + ") - Defensive player" +
                "\n\t(" + SIDES + ") - To-the-Sides player player");
        while (strategy != RANDOM  &  strategy != DEFENSIVE
                &  strategy != SIDES) {
            strategy=getStrategyScanner.nextInt();
        }
        return strategy;
    }


    /* --------------------------------------- *
     * Print the possible moves                *
     * --------------------------------------- */
    public static void printMoves(int[][] possibleMoves) {
        for (int i = 0;  i < 4;  i = i+1) {
            for (int j = 0;  j < possibleMoves.length;  j = j+1)
                System.out.print(" " + possibleMoves[j][i]);
            System.out.println();
        }
    }


    /* --------------------------------------- *
     * Mark/unmark the possible moves          *
     * --------------------------------------- */
    public static void markPossibleMoves(int[][] board, int[][] moves, int fromRow, int fromColumn, int value) {
        for (int i = 0;  i < moves.length;  i = i+1)
            if (moves[i][0] == fromRow  &  moves[i][1] == fromColumn)
                board[moves[i][2]][moves[i][3]] = value;

        showBoard(board);
    }


    /* --------------------------------------------------------------------------- *
     * Shows the board in a graphic window                                         *
     * you can use it without understanding how it works.                          *
     * --------------------------------------------------------------------------- */
    public static void showBoard(int[][] board) {
        grid.showBoard(board);
    }


    /* --------------------------------------------------------------------------- *
     * Print the board              					                           *
     * you can use it without understanding how it works.                          *
     * --------------------------------------------------------------------------- */
    public static void printMatrix(int[][] matrix){
        for (int i = matrix.length-1; i >= 0; i = i-1){
            for (int j = 0; j < matrix.length; j = j+1){
                System.out.format("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

}
