import cs.ualberta.cmput402.boardgame.board.Piece;
import cs.ualberta.cmput402.boardgame.board.Board;
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
	piece = new Piece(Board.Team.BLUE, false);
	assert(piece.isMaster() == false);
	assert(piece.getTeam().equals("BLUE"));
    }

    @Test
    public void testRedMasterPiece(){
	piece = new Piece(Board.Team.RED, true);
	assert(piece.isMaster() == true);
	assert(piece.getTeam().equals("RED"));
    }

    @Test
    public void testRedStudentPiece(){
	piece = new Piece(Board.Team.RED, false);
	assert(piece.isMaster() == false);
	assert(piece.getTeam().equals("RED"));
    }

    @Test
    public void testBlueMasterPiece(){
	piece = new Piece(Board.Team.BLUE, true);
	assert(piece.isMaster() == true);
	assert(piece.getTeam().equals("BLUE"));
    }
    
}
