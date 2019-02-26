import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.SwingRenderer;
import cs.ualberta.cmput402.boardgame.fsm.GameStateMachine;
import cs.ualberta.cmput402.boardgame.Player;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.TestCase.fail;

public class GameStateMachineTest {

    private Board board;
    private Player.Team currentTeam, idleTeam;
    private SwingRenderer renderer;
    private GameStateMachine gsm;
    
    @Before
    public void setup() {

	currentTeam = Player.Team.RED;
	
	//create mock board
	board = mock(Board.class);
	//create mock renderer
	renderer = mock(SwingRenderer.class);

	//define behaviours for board
	//	when(board.getWinner()).thenReturn(null);

	// Create State machine with mocked board and renderer.
	gsm = new GameStateMachine();
	gsm.init(renderer, board);
    }
    
    @Test
    public void testTransitions() {
        
        // Assert in first state.
	assert(gsm.getCurrentState().equals(GameStateMachine.State.Player1MoveSelection));	
        // Call move clicked with index of known move from mocked board.
	gsm.onMoveClicked(0);

        // Assert in second state.
	assert(gsm.getCurrentState().equals(GameStateMachine.State.Player1PieceSelection));
        // Call square clicked with coords of known piece of player 1.
	gsm.onSquareClicked(2, 0);
	
        // Assert in third state.
	assert(gsm.getCurrentState().equals(GameStateMachine.State.Player1DestinationSelection));
        // Call square clicked with valid coords of selected move from selected piece.
	gsm.onSquareClicked(2,2);
	
	//second player select move
	assert(gsm.getCurrentState().equals(GameStateMachine.State.Player2MoveSelection));
	gsm.onMoveClicked(0);
	
	//second player select piece                                                         
        assert(gsm.getCurrentState().equals(GameStateMachine.State.Player2PieceSelection));
	gsm.onSquareClicked(0,4);
	
	//second player select dst                                                         
        assert(gsm.getCurrentState().equals(GameStateMachine.State.Player2DestinationSelection));
	gsm.onSquareClicked(0,2);

	//when(board.getWinner()).thenAnswer(currentTeam);
	//select tiger and move red master to take blue master
	gsm.onMoveClicked(0);
	gsm.onSquareClicked(2,2);
	gsm.onSquareClicked(2,4);
	//first player advance to win condition                                              
        assert(gsm.getCurrentState().equals(GameStateMachine.State.Terminal));
    }
}
