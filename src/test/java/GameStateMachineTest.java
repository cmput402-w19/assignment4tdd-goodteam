import cs.ualberta.cmput402.boardgame.Game;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.SwingRenderer;
import cs.ualberta.cmput402.boardgame.fsm.GameStateMachine;
import cs.ualberta.cmput402.boardgame.Player;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.Before;

import static junit.framework.TestCase.fail;

public class GameStateMachineTest {

    private Board board;
    private Player player;
    private SwingRenderer renderer;
    private GameStateMachine gsm;

    @Before
    public void setup() {
//        player = mock(Player.class);
//        when(player.getMove(0)).thenReturn(Move.TIGER);
//        when(player.getMove(1)).thenReturn(Move.TIGER);
//        when(player.getMoves()).thenReturn(new Move[] { Move.TIGER, Move.TIGER });
//
//        //create mock board
//        board = mock(Board.class);
//        when(board.getCurrentPlayer()).thenReturn(player);
//        when(board.getIdlePlayer()).thenReturn(player);

        // Make the board.
        board = new Board();

        // Everyone gets Tiger!
        board.getCurrentPlayer().setMove(Move.TIGER, 0);
        board.getCurrentPlayer().setMove(Move.TIGER, 1);
        board.getIdlePlayer().setMove(Move.TIGER, 0);
        board.getIdlePlayer().setMove(Move.TIGER, 1);
        board.setExtraMove(Move.TIGER);

        //create mock renderer
        renderer = mock(SwingRenderer.class);


        //define behaviours for board
        //    when(board.getWinner()).thenReturn(null);

        // Create State machine with mocked board and renderer.
        gsm = new GameStateMachine();
        gsm.init(renderer, board);
    }

    @Test
    public void testTransitions() {
        // Assert in first state.
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Player1MoveSelection);
        // Call move clicked with index of known move from mocked board.
        gsm.onMoveClicked(0);

        // Assert in second state.
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Player1PieceSelection);
        // Call square clicked with coords of known piece of player 1.
        gsm.onSquareClicked(2, 4);

        // Assert in third state.
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Player1DestinationSelection);
        // Call square clicked with valid coords of selected move from selected piece.
        gsm.onSquareClicked(2, 2);

        //second player select move
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Player2MoveSelection);
        gsm.onMoveClicked(0);

        //second player select piece
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Player2PieceSelection);
        gsm.onSquareClicked(0, 4);

        //second player select dst
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Player2DestinationSelection);
        gsm.onSquareClicked(0,2);

        //when(board.getWinner()).thenAnswer(currentTeam);
        //select tiger and move red master to take blue master
        gsm.onMoveClicked(0);
        gsm.onSquareClicked(2,2);
        gsm.onSquareClicked(2,0);
        //first player advance to win condition
        assertEquals(gsm.getCurrentState(), GameStateMachine.State.Terminal);
    }
}
