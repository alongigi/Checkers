import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnglishCheckersUnitTest {
    @Test
    public void testCountPlayers() {
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


}
