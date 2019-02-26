package cs.ualberta.cmput402.boardgame.rendering;

import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.board.Board;

public interface GameRenderer {

    /**
     * Draws the game board.
     * @param board The board to draw.
     * @param rotated Is the board to be drawn rotated?
     */
    public void drawBoard(Board board, boolean rotated);

    /**
     * Draws the current moves.
     * @param theirs The cards at the top of the screen.
     * @param mine The cards at the bottom of the screen.
     * @param extra The extra card in the middle.
     */
    public void drawMoves(Move[] theirs, Move[] mine, Move extra);
}
