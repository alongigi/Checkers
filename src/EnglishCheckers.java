import java.util.ArrayList;
import java.util.Random;
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
	public static final int SIDES			= 3;
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

	public static int countPlayers(int[][] board, int player){
		int counter = 0;
		for(int i = 0; i<SIZE; i++){
			for(int j = 0; j<SIZE; j++){
				if((player == 1 && board[i][j] > 0) || (player == -1 && board[i][j] < 0)){
					counter++;
				}
			}
		}
		return counter;
	}


	public static int[][] createBoard() {
		int[][] board = new int[][]{
				{1,0,1,0,1,0,1,0},
				{0,1,0,1,0,1,0,1},
				{1,0,1,0,1,0,1,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,-1,0,-1,0,-1,0,-1},
				{-1,0,-1,0,-1,0,-1,0},
				{0,-1,0,-1,0,-1,0,-1}};

		return board;
	}


	public static int[][] playerDiscs(int[][] board, int player) {
		int players = countPlayers(board, player);
//		if(players == 0)
//			return new int[0][0];

		int[][] positions = new int[players][2];
		int k = 0;
		for(int i = 0; i<SIZE; i++){
			for(int j = 0; j<SIZE; j++){
				if((player == 1 && board[i][j] > 0) || (player == -1 && board[i][j] < 0)){
					positions[k][0] = i;
					positions[k][1] = j;
					k++;
				}
			}
		}
		return positions;
	}


	public static boolean isBasicMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
		if((fromRow < 0 || fromRow >= SIZE) || (fromCol < 0 || fromCol >= SIZE) || (toRow < 0 || toRow >= SIZE) || (toCol < 0 || toCol >= SIZE))
			return false;
		if(!((player == 1 && board[fromRow][fromCol] > 0) || (player == -1 && board[fromRow][fromCol] < 0)))
			return false;
		if(board[toRow][toCol] != 0)
			return false;
		if(board[fromRow][fromCol] == 1 && !(toRow - fromRow == 1 && Math.abs(toCol - fromCol) == 1))
			return false;
		else if(board[fromRow][fromCol] == -1 && !(toRow - fromRow == -1 && Math.abs(toCol - fromCol) == 1))
			return false;
		else if((board[fromRow][fromCol] == 2 || board[fromRow][fromCol] == -2) && !(Math.abs(toRow - fromRow) == 1 && Math.abs(toCol - fromCol) == 1))
			return false;
		return true;
	}


	public static int[][] getAllBasicMoves(int[][] board, int player) {
		ArrayList<int[]> listMoves = new ArrayList<>();
		int[][] positions = playerDiscs(board, player);
		for(int i = 0; i < positions.length; i++){
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]+1,positions[i][1]+1))
				listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]+1,positions[i][1]+1});
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]-1,positions[i][1]-1))
				listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]-1,positions[i][1]-1});
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]+1,positions[i][1]-1))
				listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]+1,positions[i][1]-1});
			if(isBasicMoveValid(board, player, positions[i][0], positions[i][1], positions[i][0]-1,positions[i][1]+1))
				listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]-1,positions[i][1]+1});
		}

		int[][] moves = new int[listMoves.size()][4];
		moves = listMoves.toArray(moves);

		return moves;
	}


	public static boolean isBasicJumpValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
        if((fromRow < 0 || fromRow >= SIZE) || (fromCol < 0 || fromCol >= SIZE) || (toRow < 0 || toRow >= SIZE) || (toCol < 0 || toCol >= SIZE))
            return false;
	    int rowToJump = toRow > fromRow ? fromRow + 1 : fromRow - 1;
	    int colToJump = toCol > fromCol ? fromCol + 1 : fromCol - 1;
		if(!((player == 1 && board[fromRow][fromCol] > 0) || (player == -1 && board[fromRow][fromCol] < 0)))
            return false;
        if(!((player == 1 && board[rowToJump][colToJump] < 0) || (player == -1 && board[rowToJump][colToJump] > 0)))
            return false;
        if(board[toRow][toCol] != 0)
            return false;
        if(board[fromRow][fromCol] == 1 && !(toRow - fromRow == 2 && Math.abs(toCol - fromCol) == 2))
            return false;
        else if(board[fromRow][fromCol] == -1 && !(toRow - fromRow == -2 && Math.abs(toCol - fromCol) == 2))
            return false;
        else if((board[fromRow][fromCol] == 2 || board[fromRow][fromCol] == -2) && !(Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2))
            return false;

		return true;
	}


	public static int [][] getRestrictedBasicJumps(int[][] board, int player, int row, int col) {
        ArrayList<int[]> listMoves = new ArrayList<>();
        if(isBasicJumpValid(board, player, row, col, row+2,col+2))
            listMoves.add(new int[]{row, col, row+2, col+2});
        if(isBasicJumpValid(board, player, row, col, row-2,col-2))
            listMoves.add(new int[]{row, col, row-2, col-2});
        if(isBasicJumpValid(board, player, row, col, row+2,col-2))
            listMoves.add(new int[]{row, col, row+2, col-2});
        if(isBasicJumpValid(board, player, row, col, row-2,col+2))
            listMoves.add(new int[]{row, col, row-2, col+2});
        int[][] moves = new int[listMoves.size()][4];
        moves = listMoves.toArray(moves);

        return moves;
	}


	public static int[][] getAllBasicJumps(int[][] board, int player) {
        ArrayList<int[]> listMoves = new ArrayList<>();
        int[][] positions = playerDiscs(board, player);
        for(int i = 0; i < positions.length; i++){
            if(isBasicJumpValid(board, player, positions[i][0], positions[i][1], positions[i][0]+2,positions[i][1]+2))
                listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]+2,positions[i][1]+2});
            if(isBasicJumpValid(board, player, positions[i][0], positions[i][1], positions[i][0]-2,positions[i][1]-2))
                listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]-2,positions[i][1]-2});
            if(isBasicJumpValid(board, player, positions[i][0], positions[i][1], positions[i][0]+2,positions[i][1]-2))
                listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]+2,positions[i][1]-2});
            if(isBasicJumpValid(board, player, positions[i][0], positions[i][1], positions[i][0]-2,positions[i][1]+2))
                listMoves.add(new int[]{positions[i][0], positions[i][1], positions[i][0]-2,positions[i][1]+2});
        }

        int[][] moves = new int[listMoves.size()][4];
        moves = listMoves.toArray(moves);

        return moves;
	}


	public static boolean canJump(int[][] board, int player) {
		return getAllBasicJumps(board, player).length > 0;
	}


	public static boolean isMoveValid(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
        if(!((player == 1 && board[fromRow][fromCol] > 0) || (player == -1 && board[fromRow][fromCol] < 0)))
            return false;
        if(board[toRow][toCol] != 0)
            return false;
        if(!(isBasicJumpValid(board, player, fromRow, fromCol, toRow, toCol) || (isBasicMoveValid(board, player, fromRow, fromCol, toRow, toCol) && !canJump(board, player))))
            return false;
        return true;
	}


	public static boolean hasValidMoves(int[][] board, int player) {
		return getAllBasicJumps(board, player).length > 0 || getAllBasicMoves(board, player).length > 0;
	}


	public static int[][] playMove(int[][] board, int player, int fromRow, int fromCol, int toRow, int toCol) {
	    board[toRow][toCol] = board[fromRow][fromCol];
		board[fromRow][fromCol]= 0;
	    if(board[toRow][toCol] == -1 && toRow == 0)
            board[toRow][toCol] = -2;
        if(board[toRow][toCol] == 1 && toRow == SIZE-1)
            board[toRow][toCol] = 2;
	    if(Math.abs(toRow-fromRow)==2 && Math.abs(toCol-fromCol)==2){
            int rowToJump = toRow > fromRow ? fromRow + 1 : fromRow - 1;
            int colToJump = toCol > fromCol ? fromCol + 1 : fromCol - 1;
            board[rowToJump][colToJump] = 0;
        }
		return board;
	}


	public static boolean gameOver(int[][] board, int player) {
		int players1 = countPlayers(board, 1), players2 = countPlayers(board, -1);
		return !hasValidMoves(board, player) || (players1 == 0 && players2 > 0) || (players2 == 0 && players1 > 0);
	}


	public static int findTheLeader(int[][] board) {
		int players1 = countPlayers(board, 1), players2 = countPlayers(board, -1);
        if(players1 > players2)
            return 1;
        if(players2 > players1)
            return -1;
        return 0;
	}


	public static int[][] randomPlayer(int[][] board, int player) {
        if(!hasValidMoves(board, player))
            return board;

        int[][] simpleMoves = getAllBasicMoves(board,player);
        int[][] complexMoves = getAllBasicJumps(board, player);

        if (simpleMoves.length > 0 && complexMoves.length > 0)
            if(Math.random() > 0.5) {
                int random = new Random().nextInt(simpleMoves.length);
                playMove(board,player,simpleMoves[random][0],simpleMoves[random][1],simpleMoves[random][2],simpleMoves[random][3]);
            }else{
                int random = new Random().nextInt(complexMoves.length);
                int fromRow = complexMoves[random][0], fromCol = complexMoves[random][1];
                while(getRestrictedBasicJumps(board,player,fromRow,fromCol).length > 0) {
                    playMove(board, player, complexMoves[random][0], complexMoves[random][1], complexMoves[random][2], complexMoves[random][3]);
                    fromRow = complexMoves[random][0];
                    fromCol = complexMoves[random][1];
                }
            }
        else if(simpleMoves.length > 0) {
            int random = new Random().nextInt(simpleMoves.length);
            playMove(board, player, simpleMoves[random][0], simpleMoves[random][1], simpleMoves[random][2], simpleMoves[random][3]);
        }else{
            int random = new Random().nextInt(complexMoves.length);
            int fromRow = complexMoves[random][0], fromCol = complexMoves[random][1];
            while(getRestrictedBasicJumps(board,player,fromRow,fromCol).length > 0) {
                playMove(board, player, complexMoves[random][0], complexMoves[random][1], complexMoves[random][2], complexMoves[random][3]);
                fromRow = complexMoves[random][0];
                fromCol = complexMoves[random][1];
            }
        }

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
