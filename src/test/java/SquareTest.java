import cs.ualberta.cmput402.boardgame.board.Piece;
import cs.ualberta.cmput402.boardgame.board.Square;
import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.Player;
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
	assert(square.belongsTo() == null);
	square.setShrine(Player.Team.RED);
	assert(square.isShrine() == true);
    }
    
    @Test
    public void testPlacePiece(){
        square.placePiece(Piece.RED_STUDENT);
        assert(square.getState().equals(Square.State.OCCUPIED));
    }
    
}
