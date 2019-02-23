package cs.ualberta.cmput402.boardgame.rendering;

import cs.ualberta.cmput402.boardgame.board.Board;

public interface GameRenderer {

    /**
     * Draws the game board.
     * @param board The board to draw.
     */
    public void drawBoard(Board board);
}
