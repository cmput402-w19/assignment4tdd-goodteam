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
	square.setShrine(Player.Team.RED);
	assert(square.isShrine() == true);
    }
    
    @Test
    public void testPlacePiece(){
	square.placePiece(Player.Team.RED, false);
	assert(square.getState().equals(Square.State.OCCUPIED));
    }
    
}
