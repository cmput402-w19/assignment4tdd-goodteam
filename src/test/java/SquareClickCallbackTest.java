import cs.ualberta.cmput402.boardgame.fsm.CallbackConsumer;
import cs.ualberta.cmput402.boardgame.fsm.SquareClickCallback;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.awt.Point;
import java.awt.event.ActionEvent;

public class SquareClickCallbackTest {

    CallbackConsumerMock cb;

    @Before
    public void init() {
        cb = new CallbackConsumerMock();
    }

    @Test
    public void testCallbackMade() {
        // Create click event (ActionEvent) consumer to test passing events.
        SquareClickCallback scc = new SquareClickCallback(cb, 0, 0);

        assertTrue("Callback was called before event.", !cb.wasCalled());

        // Create an action event and "fire it".
        ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_FIRST, "");
        scc.actionPerformed(ae);

        // Check that it was called.
        assertTrue("Callback was not called after event.", cb.wasCalled());
    }

    @Test
    public void testCallbackParameters() {
        // Create click event (ActionEvent) consumer to test passing some args.
        SquareClickCallback scc1 = new SquareClickCallback(cb, 4, 3);
        SquareClickCallback scc2 = new SquareClickCallback(cb, 1, 2);

        // Sanity check.
        assertTrue("Callback was called before event.", !cb.wasCalled());

        // Call results.
        Point res1 = new Point(4, 3);
        Point res2 = new Point(1, 2);

        // Create an action event, the parameters here are unimportant.
        ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_FIRST, "");

        // Fire for callback1 and check that it was called correctly.
        scc1.actionPerformed(ae);
        assertEquals("Callback got first results wrong.", res1, cb.gotParameters());

        // Fire for callback2 and check that it was called correctly.
        scc2.actionPerformed(ae);
        assertEquals("Callback got second results wrong.", res2, cb.gotParameters());

        // Retry callback1 and check that it was called correctly.
        scc1.actionPerformed(ae);
        assertEquals("Callback got third results wrong.", res1, cb.gotParameters());
    }

    // Mock Callback Consumer to test click callbacks.
    private class CallbackConsumerMock implements CallbackConsumer {
        /// Did we get called?
        private boolean called = false;

        // What parameters did we get called with.
        private int x;
        private int y;

        @Override
        public void onSquareClicked(int x, int y) {
            // Set that we got called.
            called = true;

            // Save where we got called with.
            this.x = x;
            this.y = y;
        }

        public boolean wasCalled() {
            return called;
        }

        public Point gotParameters() {
            return new Point(x, y);
        }
    }
}
