package cs.ualberta.cmput402.boardgame.fsm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareClickCallback implements ActionListener {

    /**
     * Initialise with parameters to forward to the callback.
     * @param cbc The destination to forward args to on a click event.
     * @param x The first arg, the x position of the button.
     * @param y The second arg, the y position of the button.
     */
    public SquareClickCallback(CallbackConsumer cbc, int x, int y) {

    }

    /**
     * Consume the action event and forward args to the callback.
     * @param actionEvent Information about the action performed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
