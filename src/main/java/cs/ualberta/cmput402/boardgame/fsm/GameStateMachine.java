package cs.ualberta.cmput402.boardgame.fsm;
import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.Offsets;
import cs.ualberta.cmput402.boardgame.Player;
import cs.ualberta.cmput402.boardgame.board.Piece;
import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.board.Square;
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
        renderer.setMessage("Welcome to Onitama! Player 1 select a move.");
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

    private static GameRenderer.ButtonState[][] selectablePieces(Board board) {
        // Make our states.
        int size = board.getSize();
        GameRenderer.ButtonState[][] states = new GameRenderer.ButtonState[size][size];

        // Get constant.
        Player.Team turn = board.getCurrentPlayer().getTeam();

        // Iterate squares.
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                // Sanity check.
                assert(board.onBoard(x, y));

                // Get pieces.
                Square square = board.getSquareAtPos(x, y);

                if (square.getState().equals(Square.State.OCCUPIED) && square.getPiece().getTeam().equals(turn)) {
                    states[y][x] = GameRenderer.ButtonState.CAN_SELECT;
                }
                else {
                    states[y][x] = GameRenderer.ButtonState.DEFAULT;
                }
            }
        }

        return states;
    }

    private static GameRenderer.ButtonState[][] selectableDestinations(Board board, int pX, int pY, boolean rotated) {
        // Get size.
        int size = board.getSize();

        // Get constant.
        Offsets[] origOffsets = null;
        for (Move move : board.getCurrentPlayer().getMoves()) {
            if (move.isChosen()) {
                origOffsets = move.getOffsets();
                break;
            }
        }
        assert(origOffsets != null); // Sanity check, a move must have been selected.

        // Rotate offsets if necessary.
        Offsets[] finalOffsets;
        if (!rotated) {
            finalOffsets = origOffsets;
        }
        else {
            // Empty destination array.
            finalOffsets = new Offsets[origOffsets.length];

            // Rotate all offsets around origin.
            for (int i = 0; i < origOffsets.length; ++i) {
                Offsets oldOff = origOffsets[i];
                finalOffsets[i] = new Offsets(-oldOff.xOffset, -oldOff.yOffset);
            }
        }

        // Make our states and fill with default.
        GameRenderer.ButtonState[][] states = new GameRenderer.ButtonState[size][size];
        for (int y = 0; y < size; ++y)
            for (int x = 0; x < size; ++x)
                states[y][x] = GameRenderer.ButtonState.DEFAULT;

        // Set selectable squares.
        Player.Team turn = board.getCurrentPlayer().getTeam();
        for (Offsets offset : finalOffsets) {
            // Calculate offset destination.
            int destX = pX + offset.xOffset;
            int destY = pY + offset.yOffset;


            // Set can select if on board.
            if (board.onBoard(destX, destY)) {
                // Ge the destination square.
                Square square = board.getSquareAtPos(destX, destY);

                // If it's not occupied or if it's not our team on the square.
                if (!square.getState().equals(Square.State.OCCUPIED) || !square.getPiece().getTeam().equals(turn))
                    states[destY][destX] = GameRenderer.ButtonState.CAN_SELECT;
            }
        }

        // Set selected piece.
        assert(board.onBoard(pX, pY)); // Sanity check.
        states[pY][pX] = GameRenderer.ButtonState.SELECTED;

        return states;
    }

    private static GameRenderer.ButtonState[][] getNoStateBoard(int size) {
        GameRenderer.ButtonState[][] states = new GameRenderer.ButtonState[size][size];
        for (int y = 0; y < size; ++y)
            for (int x = 0; x < size; ++x)
                states[y][x] = GameRenderer.ButtonState.DEFAULT;

        return states;
    }

    @Override
    public void onSquareClicked(int x, int y) {
    switch(currentState) {
        case Player1PieceSelection: {
            //if the current player clicks a square with its own player on it, store that coord
            Piece piece = board.getSquareAtPos(x, y).getPiece();
            if (piece != null && piece.getTeam().equals(board.getCurrentPlayer().getTeam())) {
                // Save info for next state.
                oldX = x;
                oldY = y;
                currentState = State.Player1DestinationSelection;

                // Set destination selection message.
                renderer.setMessage("Please select a destination.");

                // Set up selectable state.
                renderer.setSquareStates(selectableDestinations(board, x, y, false), false);
            }
            else {
                // Send bad piece selection message.
                renderer.setMessage("Please select a piece you own.");
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

                // Redraw moves and deselect moves and board.
                renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                        board.getExtraMove());
                renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));
                renderer.setSquareStates(getNoStateBoard(boardSize), false);

                // Check winning condition.
                if (board.getWinner() == null) {
                    // Set player 2 select move message.
                    renderer.setMessage("Player 2 please select a move.");
                    currentState = State.Player2MoveSelection;
                } else {
                    // Send player 1 wins message.
                    renderer.setMessage("Player 1 wins!");
                    currentState = State.Terminal;
                }
            }
            else {
                // Send move failed message.
                renderer.setMessage("Please select a valid move.");
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
                // Save info for next state.
                oldX = x;
                oldY = y;
                currentState = State.Player2DestinationSelection;

                // Set destination selection message.
                renderer.setMessage("Please select a destination.");

                // Set up selectable state.
                renderer.setSquareStates(selectableDestinations(board, x, y, true), true);
            }
            else {
                // Send bad piece selected message.
                renderer.setMessage("Please select a piece you own.");
            }
            break;
        }
        case Player2DestinationSelection: {
            // Get the true x, y.
            x = (boardSize - 1) - x;
            y = (boardSize - 1) - y;

            // Play the piece and redraw the board.
            if (board.playPiece(oldX, oldY, x, y)) {
                renderer.drawBoard(board, false);

                // Swap the moves and turn.
                board.swapMoves();
                board.otherPlayerTurn();

                // Redraw moves and deselect moves and board.
                renderer.drawMoves(board.getIdlePlayer().getMoves(), board.getCurrentPlayer().getMoves(),
                        board.getExtraMove());
                renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));
                renderer.setSquareStates(getNoStateBoard(boardSize), false);

                // Check winning condition.
                if (board.getWinner() == null) {
                    // Set player 1 select move message.
                    renderer.setMessage("Player 1 please select a move.");
                    currentState = State.Player1MoveSelection;
                } else {
                    // Send player 2 wins message.
                    renderer.setMessage("Player 2 wins!");
                    currentState = State.Terminal;
                }
            }
            else {
                // Send move failed message.
                renderer.setMessage("Please select a valid move.");
            }
            break;
        }
        case Terminal:
            break;
        default:
            // Send bad state message.
            renderer.setMessage("Please select a move.");
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
                renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));

                // Set selectable pieces.
                renderer.setSquareStates(selectablePieces(board), false);

                // Send selecct piece message.
                renderer.setMessage("Please select a piece to move.");

                break;
            }
            case Player2MoveSelection: {
                // Select the move.
                board.selectMove(idx);
                currentState = State.Player2PieceSelection;

                // Set move selection.
                renderer.setMoveStates(movesToStates(board.getCurrentPlayer().getMoves()));

                // Set selectable pieces.
                renderer.setSquareStates(selectablePieces(board), true);

                // Send selecct piece message.
                renderer.setMessage("Please select a piece to move.");

                break;
            }
            case Terminal:
                break;
            default:
                // Send bad state message.
                renderer.setMessage("Please select a square.");
                break;
        }
    }

}
