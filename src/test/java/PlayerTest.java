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
	assert(player.getMoves().size() == 0);
    }

    @Test
    public void testPlayerMoves(){
	player.setMove(Move.TIGER);
	assert(player.getMoves().size() == 1);
	player.removeMove(0);
	assert(player.getMoves().size() == 0);
    }

}


    
