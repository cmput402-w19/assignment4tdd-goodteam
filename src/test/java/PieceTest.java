import cs.ualberta.cmput402.boardgame.board.Piece;
import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;

/**                                                                           
 * Created by knewbury on 02-22-19                                             
 * This one is a bit overboard I'll admit
 */

public class PieceTest {

    public Piece piece;

    @Test
    public void testBlueStudentPiece(){
	piece = new Piece(Player.Team.BLUE, false);
	assert(piece.isMaster() == false);
	assert(piece.getTeam().equals(Player.Team.BLUE));
    }

    @Test
    public void testRedMasterPiece(){
	piece = new Piece(Player.Team.RED, true);
	assert(piece.isMaster() == true);
	assert(piece.getTeam().equals(Player.Team.RED));
    }

    @Test
    public void testRedStudentPiece(){
	piece = new Piece(Player.Team.RED, false);
	assert(piece.isMaster() == false);
	assert(piece.getTeam().equals(Player.Team.RED));
    }

    @Test
    public void testBlueMasterPiece(){
	piece = new Piece(Player.Team.BLUE, true);
	assert(piece.isMaster() == true);
	assert(piece.getTeam().equals(Player.Team.BLUE));
    }
    
}
