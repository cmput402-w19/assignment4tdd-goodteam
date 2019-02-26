package cs.ualberta.cmput402.boardgame.fsm;

import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.GameRenderer;

public class GameStateMachine implements CallbackConsumer{

    public enum State {
	fakestate,
        Player1MoveSelection,
        Player1PieceSelection,
        Player1DestinationSelection,
        Player2MoveSelection,
        Player2PieceSelection,
        Player2DestinationSelection,
	Terminal;
    }

    public GameStateMachine() {

    }

    public void init(GameRenderer renderer, Board board) {

    }


    public State getCurrentState(){
	return State.fakestate;
    }

    @Override
    public void onSquareClicked(int x, int y) {

    }

    @Override
    public void onMoveClicked(int idx) {

    }

}
