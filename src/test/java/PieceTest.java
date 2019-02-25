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

    private Piece piece;

    @Test
    public void testBlueStudentPiece(){
        piece = Piece.BLUE_STUDENT;
        assert(!piece.isMaster());
        assert(piece.getTeam().equals(Board.Team.BLUE));
    }

    @Test
    public void testRedMasterPiece(){
        piece = Piece.RED_MASTER;
        assert(piece.isMaster());
        assert(piece.getTeam().equals(Board.Team.RED));
    }

    @Test
    public void testRedStudentPiece(){
        piece = Piece.RED_STUDENT;
        assert(!piece.isMaster());
        assert(piece.getTeam().equals(Board.Team.RED));
    }

    @Test
    public void testBlueMasterPiece() {
        piece = Piece.BLUE_MASTER;
        assert (piece.isMaster());
        assert (piece.getTeam().equals(Board.Team.BLUE));
    }
}
