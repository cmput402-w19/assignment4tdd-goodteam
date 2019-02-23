import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;
import cs.ualberta.cmput402.boardgame.Offsets;
import cs.ualberta.cmput402.boardgame.Move;
/**                                                                                        
 * Created by knewbury on 02-23-19.                                                         
 */
public class MoveTest {

    private Move move;

    @Before
    public void setup() {
        move = Move.DRAGON;
    }


    @Test
    public void testGetOffsets() {
	assert(move.getOffsets()[0].equals(new Offsets(2, 1)));
    }

    @Test
    public void testSelectChosen() {
	assert(move.isChosen() == false);
	move.select();
	assert(move.isChosen() == true);
    }
}
