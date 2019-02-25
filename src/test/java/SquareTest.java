import cs.ualberta.cmput402.boardgame.board.Piece;
import cs.ualberta.cmput402.boardgame.board.Square;
import cs.ualberta.cmput402.boardgame.board.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;

/**                                                                           
 * Created by knewbury on 02-22-19                                             
 */
public class SquareTest {

    public Square square;
    
    @Before
    public void setup() {
        square = new Square();
    }

    @Test
    public void testInitialState(){
	assert(square.getState().equals(Square.State.EMPTY));
    }

    @Test
    public void testIsShrine(){
	square.setShrine();
	assert(square.isShrine() == true);
    }
    
    @Test
    public void testPlacePiece(){
        square.placePiece(Piece.RED_STUDENT);
        assert(square.getState().equals(Square.State.OCCUPIED));
    }
    
}
