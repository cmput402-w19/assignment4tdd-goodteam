import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.Player;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;


/**                                                                                           
 * Created by knewbury on 02-24-19                                                            
 */
public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        player = new Player(Player.Team.RED);
    }

    @Test
    public void testInitPlayer(){
	assert(player.getTeam().equals(Player.Team.RED));
	assert(player.getMove(0) == null);
    }

    @Test
    public void testPlayerMoves(){
	player.setMove(Move.TIGER, 0);
	assert(player.getMove(0).equals(Move.TIGER));
	player.removeMove(0);
	assert(player.getMove(0) == null);
    }

}


    
