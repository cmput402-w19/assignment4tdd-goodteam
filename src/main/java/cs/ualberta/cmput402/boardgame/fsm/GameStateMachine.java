package cs.ualberta.cmput402.boardgame.fsm;

import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.GameRenderer;

public class GameStateMachine implements CallbackConsumer {

    public enum State {
        Player1MoveSelection,
        Player1PieceSelection,
        Player1DestinationSelection,
        Player2MoveSelection,
        Player2PieceSelection,
        Player2DestinationSelection,
        Terminal;
    }

    // Current finite state machine state.
    private State currentState;
    private Board board;
    private GameRenderer renderer;

    private Move moveToPlay;

    // Do nothing constructor.
    public GameStateMachine() { }

    // Init with things to interact with.
    public void init(GameRenderer renderer, Board board) {
        currentState = State.Player1MoveSelection;
        this.renderer = renderer;
        this.board = board;
        renderer.drawBoard(board, false);
        renderer.drawMoves(board.getCurrentPlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                board.getExtraMove());
    }

    public State getCurrentState(){
        return currentState;
    }

    @Override
    public void onSquareClicked(int x, int y) {
    switch(currentState){
        case Player1PieceSelection:
	    
            break;
        case Player1DestinationSelection:
            break;
        case Player2PieceSelection:
            break;
        case Player2DestinationSelection:
            break;
        default:
            break;
        }
    }


    @Override
    public void onMoveClicked(int idx) {
    switch(currentState){
        case Player1MoveSelection:
            moveToPlay = board.getCurrentPlayer().getMove(idx);
            currentState = Player1PieceSelection;
            break;
        case Player2MoveSelection:
            break;
        default:
            break;
        }
    }

}
