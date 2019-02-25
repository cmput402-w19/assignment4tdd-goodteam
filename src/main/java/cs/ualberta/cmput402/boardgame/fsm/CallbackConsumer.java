package cs.ualberta.cmput402.boardgame.fsm;

public interface CallbackConsumer {

    /**
     * Called when a gameboard square is clicked with information about which square was clicked.
     * @param x The x position of the square that got clicked.
     * @param y The y position of the square that got clicked.
     */
    public void onSquareClicked(int x, int y);

    /**
     * Called when a move is clicked with information about which move was clicked.
     * @param idx The index of the move that was clicked for the currently active player.
     */
    public void onMoveClicked(int idx);
}
