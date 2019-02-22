import cs.ualberta.cmput402.boardgame.board.Square;
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
	assert(square.toString().equals("EMPTY"));
    }

    @Test
    public void testIsShrine(){
	square.setShrine();
	assert(square.isShrine() == true);
    }
    
    @Test
    public void testPlacePiece(){
	square.placePiece("");
	assert(square.toString().equals("OCCUPIED"));
    }
    
}
