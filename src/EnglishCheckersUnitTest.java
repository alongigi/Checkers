import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EnglishCheckersUnitTest {
    @Test
    public void countPlayersTest() {
        int[][] board = new int[][]{
                {1,0,1,0,1,0,1,0},
                {0,1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,-1,0,-1,0,-1,0,-1},
                {-1,0,-1,0,-1,0,-1,0},
                {0,-1,0,-1,0,-1,0,-1}};

        int players1 = EnglishCheckers.countPlayers(board, 1);
        int players2 = EnglishCheckers.countPlayers(board, -1);
        assertEquals(12, players1);
        assertEquals(12, players2);

    }

    @Test
    public void playerDiscsTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,-1,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,-2,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,2,0,0,0,0}};

        int[][] discs = EnglishCheckers.playerDiscs(board, 1);
        assertArrayEquals(new int[][] {{2,0},{2,2},{7,3}}, discs);
    }

    @Test
    public void isBasicMoveValidTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,-1,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,-2,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,2,0,0,0,0}};

        boolean isBasicMoveValid1= EnglishCheckers.isBasicMoveValid(board, 1, 2, 2, 3, 3);
        boolean isBasicMoveValid2 = EnglishCheckers.isBasicMoveValid(board, 1, 7, 3, 6, 2);
        assertTrue(isBasicMoveValid1);
        assertFalse(isBasicMoveValid2);
    }

    @Test
    public void getAllBasicMovesTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,-1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] basicMoves = EnglishCheckers.getAllBasicMoves(board, -1);
        assertArrayEquals(new int[][] {{3,4,2,3},{5,2,4,1},{5,2,4,3}}, basicMoves);
    }

    @Test
    public void isBasicJumpValidTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,-1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boolean isBasicJumpValid1= EnglishCheckers.isBasicJumpValid(board, 1, 2, 5, 4, 3);
        boolean isBasicJumpValid2= EnglishCheckers.isBasicJumpValid(board, -1, 5, 2, 3, 0);

        assertTrue(isBasicJumpValid1);
        assertFalse(isBasicJumpValid2);
    }

    @Test
    public void getRestrictedBasicJumpsTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,-1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] restrictedBasicJumps1 = EnglishCheckers.getRestrictedBasicJumps(board, 1, 2, 5);
        int[][] restrictedBasicJumps2 = EnglishCheckers.getRestrictedBasicJumps(board, -1, 5, 2);
        assertArrayEquals(new int[][]{{2, 5, 4, 3}}, restrictedBasicJumps1);
        assertArrayEquals(new int[][]{}, restrictedBasicJumps2);

    }

    @Test
    public void getAllBasicJumpsTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {1,0,0,0,0,0,0,0},
                {0,-1,0,0,0,0,0,0},
                {2,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] allBasicJumps1 = EnglishCheckers.getAllBasicJumps(board, 1);
        int[][] allBasicJumps2 = EnglishCheckers.getAllBasicJumps(board, -1);
        assertArrayEquals(new int[][]{{6, 0, 4, 2}}, allBasicJumps1);
        assertArrayEquals(new int[][]{}, allBasicJumps2);
    }

    @Test
    public void canJumpTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {2,0,-1,0,0,0,0,0},
                {0,-2,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boolean canJump1 = EnglishCheckers.canJump(board, 1);
        boolean canJump2 = EnglishCheckers.canJump(board, -1);
        assertTrue(canJump1);
        assertFalse(canJump2);
    }

    @Test
    public void isMoveValidTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {-1,0,0,0,-2,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boolean isMoveValid1 = EnglishCheckers.isMoveValid(board, 1, 3, 3, 4, 2);
        boolean isMoveValid2 = EnglishCheckers.isMoveValid(board, -1, 6, 4, 2, 4);
        boolean isMoveValid3 = EnglishCheckers.isMoveValid(board, -1, 6, 4, 4, 2);
        boolean isMoveValid4 = EnglishCheckers.isMoveValid(board, 1, 3, 3, 2, 2);
        boolean isMoveValid5 = EnglishCheckers.isMoveValid(board, -1, 6, 0, 5, 1);

        assertFalse(isMoveValid1);
        assertFalse(isMoveValid2);
        assertTrue(isMoveValid3);
        assertFalse(isMoveValid4);
        assertFalse(isMoveValid5);
    }

    @Test
    public void hasValidMovesTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {1,0,0,0,0,0,0,0},
                {0,-2,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boolean hasValidMoves1 = EnglishCheckers.hasValidMoves(board, 1);
        boolean hasValidMoves2 = EnglishCheckers.hasValidMoves(board, -1);
        assertTrue(hasValidMoves1);
        assertTrue(hasValidMoves2);

        board = new int[][]{
                {0,0,1,0,1,0,0,0},
                {0,0,0,-1,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boolean hasValidMoves3 = EnglishCheckers.hasValidMoves(board, -1);
        assertFalse(hasValidMoves3);
    }

    @Test
    public void playMoveTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,1,0,1,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] boardPlayMove = new int[][]{
                {0,0,0,0,-2,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] newBoard = EnglishCheckers.playMove(board, -1, 2, 2, 0, 4);
        assertArrayEquals(newBoard, boardPlayMove);
    }

    @Test
    public void gameOverTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,1,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boolean gameOver = EnglishCheckers.gameOver(board, -1);
        assertTrue(gameOver);
    }

    @Test
    public void findTheLeaderTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,-1,0,0},
                {1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,-2,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,2,0,0,0,0}};

        int leader = EnglishCheckers.findTheLeader(board);
        assertEquals(leader, 0);
    }

    @Test
    public void randomPlayerTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] boardPlayMove = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,-1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] newBoard = EnglishCheckers.randomPlayer(board, -1);
        assertArrayEquals(newBoard, boardPlayMove);

        board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,2,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boardPlayMove = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,2,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0}};

        newBoard = EnglishCheckers.randomPlayer(board, 1);
        assertArrayEquals(newBoard, boardPlayMove);
    }

    @Test
    public void defensivePlayerTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] boardPlayMove = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0},
                {0,-1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] newBoard = EnglishCheckers.defensivePlayer(board, -1);
        assertArrayEquals(newBoard, boardPlayMove);

        board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,-1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boardPlayMove = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,0,0,0,-1,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        newBoard = EnglishCheckers.defensivePlayer(board, 1);
        assertArrayEquals(newBoard, boardPlayMove);
    }

    @Test
    public void sidesPlayerTest() {
        int[][] board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] boardPlayMove = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,-1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        int[][] newBoard = EnglishCheckers.sidesPlayer(board, -1);
        assertArrayEquals(newBoard, boardPlayMove);

        board = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        boardPlayMove = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,-1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}};

        newBoard = EnglishCheckers.sidesPlayer(board, 1);
        assertArrayEquals(newBoard, boardPlayMove);
    }
}
