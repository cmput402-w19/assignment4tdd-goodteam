import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;
import cs.ualberta.cmput402.boardgame.Offsets;
import cs.ualberta.cmput402.boardgame.Move;
/**                                                                                        
 * Created by knewbury on 02-23-19.                                                         
 */
public class MoveTest {

    private Move dragonMove;
    private Move gooseMove;
    private Move elephantMove;
    private Move monkeyMove;
    private Move tigerMove;
    private Offsets offsets1, offsets2, offsets3;

    @Before
    public void setup() {
        dragonMove = Move.DRAGON;
	gooseMove = Move.GOOSE;
	elephantMove = Move.ELEPHANT;
	monkeyMove = Move.MONKEY;
	tigerMove = Move.TIGER;
	offsets1 = new Offsets(-1, 1);
	offsets2 = new Offsets(-2, 1);
	offsets3 = new Offsets(0, 2);
    }


    @Test
    public void testGetOffsetsDragonMove() {
	assert(dragonMove.getOffsets()[0].equals(offsets2));
    }

    @Test
    public void testGetOffsetsGooseMove() {
        assert(gooseMove.getOffsets()[0].equals(offsets1));
    }

    @Test
    public void testGetOffsetsElephantMove() {
        assert(elephantMove.getOffsets()[0].equals(offsets1));
    }

    @Test
    public void testGetOffsetsMonkeyMove() {
        assert(monkeyMove.getOffsets()[0].equals(offsets1));
    }

    @Test
    public void testGetOffsetsTigerMove() {
        assert(tigerMove.getOffsets()[0].equals(offsets3));
    }    
    
    @Test
    public void testSelectChosen() {
	assert(dragonMove.isChosen() == false);
	dragonMove.select();
	assert(dragonMove.isChosen() == true);
    }
}
