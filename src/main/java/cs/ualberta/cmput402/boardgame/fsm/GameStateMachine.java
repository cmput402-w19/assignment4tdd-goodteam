package cs.ualberta.cmput402.boardgame.fsm;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.board.Piece;
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
    private int oldx, oldy;
    private int boardSize;
    
    // Do nothing constructor.
    public GameStateMachine() { }

    // Init with things to interact with.
    public void init(GameRenderer renderer, Board board) {
        currentState = State.Player1MoveSelection;
        this.renderer = renderer;
        this.board = board;
	this.boardSize = board.getSize();
        renderer.drawBoard(board, false);
        renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                board.getExtraMove());
    }

    public State getCurrentState(){
        return currentState;
    }

    @Override
    public void onSquareClicked(int x, int y) {
    switch(currentState){
        case Player1PieceSelection:
	    //if the current player clicks a square with its own player on it, store that coord
	    Piece temppiece = board.getSquareAtPos(x,y).getPiece();
	    if(temppiece != null && temppiece.getTeam().equals(board.getCurrentPlayer().getTeam())){
		oldx = x;
		oldy = y;
	    }
	    currentState = State.Player1DestinationSelection;
            break;
        case Player1DestinationSelection:
	    board.playPiece(oldx, oldy, x, y);
	    renderer.drawBoard(board, true);
	    board.otherPlayerTurn();
	    if(board.getWinner() == null){
		currentState = State.Player2MoveSelection;
	    }else{
		currentState = State.Terminal;
	    }
            break;
        case Player2PieceSelection:
	    //if the current player clicks a square with its own player on it, store that coord            
            Piece piece = board.getSquareAtPos(((boardSize-1)-x),((boardSize-1)-y)).getPiece();
            if(piece != null && piece.getTeam().equals(board.getCurrentPlayer().getTeam())){
                oldx = x;
                oldy = y;
            }
            currentState = State.Player2DestinationSelection;
            break;
        case Player2DestinationSelection:
	    board.playPiece(((boardSize-1)-oldx), ((boardSize-1)-oldy), ((boardSize-1)-x), ((boardSize-1)-y));
	    renderer.drawBoard(board, false);
            board.otherPlayerTurn();
            if(board.getWinner() == null){
		currentState = State.Player1MoveSelection;
            }else{
		currentState = State.Terminal;
            }
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
	    moveToPlay.select();
	    board.swapMoves();
	    renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                board.getExtraMove());
	    currentState = State.Player1PieceSelection;
            break;
        case Player2MoveSelection:
	    moveToPlay = board.getCurrentPlayer().getMove(idx);
	    moveToPlay.select();
	    board.swapMoves();
	    renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                board.getExtraMove());
	    currentState = State.Player2PieceSelection;
            break;
        default:
            break;
        }
    }

}
