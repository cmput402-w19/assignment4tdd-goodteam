import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.board.Square;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.Player;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.TestClass;


/**
 * Created by knewbury on 02-21-19
 */
public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void testInitState() {
	assert(board.getSize() == 5);
	assert (board.getWinner() == null);
	assert (board.getCurrentPlayer().getTeam().equals(Player.Team.RED));
    }
    @Test
    public void testInitMid() {

	assert(board.getSize() != 0);
	
	//first and last row will have players on them so omit in this test
	for (int i = 1; i < board.getSize()-1; i++) {
	    for (int j = 0; j < board.getSize(); j++) {
                assert (board.getSquareAtPos(i, j).getState().equals(Square.State.EMPTY));
            }
        }
    }
    @Test
    public void testInitPieces() {

	assert(board.getSize() != 0);
	
	//now test pieces were placed
	for (int i = 0; i <1; i++) {
	    for (int j = 0; j < board.getSize(); j++) {
		assert (board.getSquareAtPos(i, j).getState().equals(Square.State.OCCUPIED));
	    }
	}
	for (int i = board.getSize(); i <board.getSize()-1; i++) {
            for (int j = 0; j < board.getSize(); j++) {
		assert (board.getSquareAtPos(i, j).getState().equals(Square.State.OCCUPIED));
            }
        }
	
    }

    @Test
    public void testInitMidShrine(){
	assert (board.getSquareAtPos(0, 2).isShrine() == true);
	assert (board.getSquareAtPos(4, 2).isShrine() == true);
    }

    @Test
    public void testOtherPlayerTurn(){
	assert (board.getCurrentPlayer().getTeam().equals(Player.Team.RED));
	board.otherPlayerTurn();
	assert (board.getCurrentPlayer().getTeam().equals(Player.Team.BLUE));
       
    }

    @Test
    public void testInitMoves(){
	ArrayList<Move> firstPMoves = board.getCurrentPlayer().getMoves();
	
	assert(firstPMoves.size() == 2);
	//want to test that both players have 2 moves, could write getter
	//for other player but it would only be used here...
	board.otherPlayerTurn();
	ArrayList<Move> secondPMoves = board.getCurrentPlayer().getMoves();
	assert(secondPMoves.size() == 2);
	Move extraMove = board.getExtraMove();
	//dont care what the actual moves are, just that no one was double placed
	assert((!extraMove.equals(firstPMoves.get(0))) && (!extraMove.equals(firstPMoves.get(1))) && (!extraMove.equals(secondPMoves.get(0))) && (!extraMove.equals(secondPMoves.get(1))));
    }

    @Test
    public void testSelectMove(){
	board.selectMove(0);
	assert(board.getCurrentPlayer().getMoves().get(0).isChosen());
	board.deselectMove(0);
    }

    @Test
    public void testPlayPieceInvalid(){
	//all four edges checked
	assert(!(board.playPiece(0, 0, -1, 0)));
	assert(!board.playPiece(0,0, 0, -1));
	assert(!board.playPiece(0,0,5, 0));
	assert(!board.playPiece(0,0,0, 5));
	       
    }    

    @Test
    public void testPlayPieceValid(){
	//can move to location of other color, or empty square, both valid
	int x = 4;
	int y = 0;
	assert(board.playPiece(0,0,x, y));
	assert (board.getSquareAtPos(x, y).getState().equals(Square.State.OCCUPIED));
	assert (board.getSquareAtPos(x, y).getPiece().getTeam().equals(Player.Team.RED));
	int xx = 1;
	assert(board.playPiece(0,1,xx, y));
	assert (board.getSquareAtPos(xx, y).getState().equals(Square.State.OCCUPIED));
        assert (board.getSquareAtPos(xx, y).getPiece().getTeam().equals(Player.Team.RED));

    }

}
