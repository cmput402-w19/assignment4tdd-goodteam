package cs.ualberta.cmput402.boardgame.fsm;

import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.GameRenderer;

public class GameStateMachine implements CallbackConsumer{


    private enum State {

        Player1MoveSelection,
        Player1PieceSelection,
        Player1DestinationSelection,
        Player2MoveSelection,
        Player2PieceSelection,
        Player2DestinationSelection,
	Terminal;
    }
  
    private State currentState;



    public GameStateMachine() {

    }

    public void init(GameRenderer renderer, Board board) {
	currentState = State.Player1MoveSelection;
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
	    break;
	case Player2MoveSelection:
	    break;
	default:
	    break;
	}

    }

}
