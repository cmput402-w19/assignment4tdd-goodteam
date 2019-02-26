package cs.ualberta.cmput402.boardgame.fsm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveClickCallback implements ActionListener {

    /**
     * The consumer of this actual event.
     */
    private CallbackConsumer cbc;

    /**
     * The position that was clicked.
     */
    int idx;

    /**
     * Initialise with parameters to forward to the callback.
     * @param cbc The destination to forward args to on a click event.
     * @param idx The index of the move that was clicked.
     */
    public MoveClickCallback(CallbackConsumer cbc, int idx) {
        this.cbc = cbc;
        this.idx = idx;
    }

    /**
     * Consume the action event and forward args to the callback.
     * @param actionEvent Information about the action performed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        cbc.onMoveClicked(idx);
    }
}
