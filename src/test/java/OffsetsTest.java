import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;
import cs.ualberta.cmput402.boardgame.Offsets;

/**                                                                                        * Created by knewbury on 02-23-19.                                                       
*/

public class OffsetsTest {

    private Offsets first , second, third, fourth, fifth;
    
    @Before
    public void setup() {
	first = new Offsets(1, 1);
	second = new Offsets(1, 1);
	third = new Offsets(2, 1);
	fourth = new Offsets(1, 2);
	fifth = new Offsets(2, 2);
    }

    @Test
    public void testEqualSelf(){
	assert(first.equals(first));
    }

    @Test
    public void testEqualOther(){
	assert(first.equals(second));
    }

    @Test
    public void testNotEqualOther(){
	assert(!first.equals(third));
	assert(!first.equals(fourth));
	assert(!first.equals(fifth));
    }
    
    
}
