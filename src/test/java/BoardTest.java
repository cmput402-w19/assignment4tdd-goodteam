import cs.ualberta.cmput402.boardgame.board.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;

/**
 * Created by knewbury on 02-21-19
 */
public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void testInitState() {
	assert(board.getSize() == 5);
	assert (board.getWinner() == null);
	assert (board.getCurrentPlayer().toString().equals("RED"));
    }
    @Test
    public void testInitMid() {

	assert(board.getSize() != 0);
	
	//first and last row will have players on them so omit in this test
	for (int i = 1; i < board.getSize()-1; i++) {
	    for (int j = 0; j < board.getSize(); j++) {
                assert (board.getSquareAtPos(i, j).getState().equals("EMPTY"));
            }
        }
    }
    @Test
    public void testInitPieces() {

	assert(board.getSize() != 0);
	
	//now test pieces were placed
	for (int i = 0; i <1; i++) {
	    for (int j = 0; j < board.getSize(); j++) {
		assert (board.getSquareAtPos(i, j).getState().equals("OCCUPIED"));
	    }
	}
	for (int i = board.getSize(); i <board.getSize()-1; i++) {
            for (int j = 0; j < board.getSize(); j++) {
		assert (board.getSquareAtPos(i, j).getState().equals("OCCUPIED"));
            }
        }
	
    }

   @Test
   public void testInitMidShrine(){
       assert (board.getSquareAtPos(0, 2).isShrine() == true);
       assert (board.getSquareAtPos(4, 2).isShrine() == true);
   }

}
