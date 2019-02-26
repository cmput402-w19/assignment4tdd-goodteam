package cs.ualberta.cmput402.boardgame.rendering;

import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.board.Board;

public interface GameRenderer {

    public enum ButtonState { DEFAULT, SELECTED, CAN_SELECT }

    /**
     * Draws the game board.
     * @param board The board to draw.
     * @param rotated Is the board to be drawn rotated?
     */
    public void drawBoard(Board board, boolean rotated);

    /**
     * Set the state of all tiles.
     *
     * @param states The states to set.
     * @param rotated Is the board to be drawn rotated?
     */
    public void setSquareStates(ButtonState[][] states, boolean rotated);

    /**
     * Draws the current moves.
     * @param theirs The cards at the top of the screen.
     * @param mine The cards at the bottom of the screen.
     * @param extra The extra card in the middle.
     */
    public void drawMoves(Move[] theirs, Move[] mine, Move extra);

}
