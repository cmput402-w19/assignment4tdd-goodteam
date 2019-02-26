package cs.ualberta.cmput402.boardgame.fsm;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.board.Piece;
import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.GameRenderer;

import java.util.Arrays;

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
    private int oldX, oldY;
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

    public State getCurrentState() {
        return currentState;
    }

    private static GameRenderer.ButtonState[] movesToStates(Move[] moves) {
        // Get length.
        int length = moves.length;

        // Make moves.
        GameRenderer.ButtonState[] states = new GameRenderer.ButtonState[length];

        // Iterate moves and fill states.
        for (int i = 0; i < length; ++i) {
            if (moves[i].isChosen())
                states[i] = GameRenderer.ButtonState.SELECTED;
            else
                states[i] = GameRenderer.ButtonState.DEFAULT;
        }

        return states;
    }

    @Override
    public void onSquareClicked(int x, int y) {
    switch(currentState) {
        case Player1PieceSelection: {
            //if the current player clicks a square with its own player on it, store that coord
            Piece piece = board.getSquareAtPos(x, y).getPiece();
            if (piece != null && piece.getTeam().equals(board.getCurrentPlayer().getTeam())) {
                oldX = x;
                oldY = y;
                currentState = State.Player1DestinationSelection;
            }
            else {
                // Send error message.
            }
            break;
        }
        case Player1DestinationSelection: {
            // If we succeed in playing the piece, go ahead with the transition.
            if (board.playPiece(oldX, oldY, x, y)) {
                // Redraw the board.
                renderer.drawBoard(board, true);

                // Swap the moves and turn.
                board.swapMoves();
                board.otherPlayerTurn();

                // Redraw moves and deselect moves.
                renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                        board.getExtraMove());
                renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));

                // Check winning condition.
                if (board.getWinner() == null) {
                    currentState = State.Player2MoveSelection;
                } else {
                    currentState = State.Terminal;
                }

            }
            break;
        }
        case Player2PieceSelection: {
            // Get the true x, y.
            x = (boardSize - 1) - x;
            y = (boardSize - 1) - y;

            //if the current player clicks a square with its own player on it, store that coord
            Piece piece = board.getSquareAtPos(x, y).getPiece();
            if (piece != null && piece.getTeam().equals(board.getCurrentPlayer().getTeam())) {
                oldX = x;
                oldY = y;

                currentState = State.Player2DestinationSelection;
            }
            else {
                // Send error message.
            }
            break;
        }
        case Player2DestinationSelection: {
            // Get the true x, y.
            x = (boardSize - 1) - x;
            y = (boardSize - 1) - y;

            // Play the piece and redraw the board.
            board.playPiece(oldX, oldY, x, y);
            renderer.drawBoard(board, false);

            // Swap the moves and turn.
            board.swapMoves();
            board.otherPlayerTurn();

            // Redraw moves and deselect moves.
            renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                    board.getExtraMove());
            renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));

            // Check winning condition.
            if (board.getWinner() == null) {
                currentState = State.Player1MoveSelection;
            } else {
                currentState = State.Terminal;
            }

            break;
        }
        default:
            break;
        }
    }


    @Override
    public void onMoveClicked(int idx) {
        switch(currentState) {
            case Player1MoveSelection: {
                // Select the move.
                board.selectMove(idx);
                currentState = State.Player1PieceSelection;

                // Set move selection.
                int length = board.getCurrentPlayer().getMoves().length;
                GameRenderer.ButtonState[] states = new GameRenderer.ButtonState[length];
                Arrays.fill(states, GameRenderer.ButtonState.DEFAULT);
                states[idx] = GameRenderer.ButtonState.SELECTED;
                renderer.setMoveStates(states);

                break;
            }
            case Player2MoveSelection: {
                // Select the move.
                board.selectMove(idx);
                currentState = State.Player2PieceSelection;

                // Set move selection.
                renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));

                break;
            }
            default:
                break;
        }
    }

}
