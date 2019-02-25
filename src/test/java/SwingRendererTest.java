import cs.ualberta.cmput402.boardgame.fsm.CallbackConsumer;
import cs.ualberta.cmput402.boardgame.rendering.SwingRenderer;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;

public class SwingRendererTest {
    @Test
    public void testCardGUICreation() {
        // Constants
        Dimension boardDim = new Dimension(5, 5);
        CallbackStub cbs = new CallbackStub();

        // Test a few card counts.
        for (int i = 2; i < 5; ++i) {
            // Get the gui.
            SwingRenderer renderer = new SwingRenderer(cbs, boardDim, i);
            JPanel moveGui = renderer.getMoveGui();

            // Get the components. We should have only have a panel for layout constraints as a subcomponent.
            Component[] constraintInternalComps = moveGui.getComponents();
            assertEquals("Move gui did not have correct component count",
                    1, constraintInternalComps.length);

            // Get the gridbaglayout component.
            Component gridBagPanelComp = constraintInternalComps[0];
            assertThat("Grid bag panel incorrect type.", gridBagPanelComp, instanceOf(JPanel.class));
            JPanel gridBagPanel = (JPanel) gridBagPanelComp;

            // Ensure correct constraints layout.
            LayoutManager gridBagPanelLayout = gridBagPanel.getLayout();
            assertThat("Constraints layout incorrect type.", gridBagPanelLayout, instanceOf(GridBagLayout.class));

            // Get the components. We should have only the move grid as a subcomponent.
            Component[] gridBagPanelInternalComps = gridBagPanel.getComponents();
            assertEquals("Move gui constraints did not have correct component count",
                    1, gridBagPanelInternalComps.length);

            // Get gridlayout component.
            Component gridPanelComp = gridBagPanelInternalComps[0];
            assertThat("Grid layout incorrect type.", gridPanelComp, instanceOf(JPanel.class));
            JPanel gridPanel = (JPanel) gridPanelComp;

            // Ensure correct constraints layout.
            LayoutManager gridPanelLayout = gridPanel.getLayout();
            assertThat(gridPanelLayout, instanceOf(GridLayout.class));
            GridLayout gridLayout = (GridLayout) gridPanelLayout;

            // Finally ensure that the rows and columns were set up correctly.
            assertEquals("Wrong number of move rows.", 3, gridLayout.getRows());
            assertEquals("Wrong number of move columns.", i, gridLayout.getColumns());

            // Get components and check that all of the grid is filled as it should be.
            Component[] gridComps = gridPanel.getComponents();
            assertEquals(String.format("Grid did not have correct number of components. (3 * %d).", i),
                    3 * i, gridComps.length);

            // Check components in first row. All should be buttons.
            for (int j = 0; j < i; ++j) {
                assertThat(String.format("Grid layout incorrect type (%d, 0)", j),
                        gridComps[j], instanceOf(JButton.class));
            }

            // Check second row. First should be a component holding the image of the shared move, the rest should be
            // blank JPanel.
            assertThat("Grid layout incorrect type (0, 1)", gridComps[i], instanceOf(JLabel.class));
            for (int j = 1; j < i; ++j) {
                assertThat(String.format("Grid layout incorrect type (%d, 1)", j),
                        gridComps[i + j], instanceOf(JPanel.class));
            }

            // Check components in last row. All should be buttons.
            for (int j = 0; j < i; ++j) {
                assertThat(String.format("Grid layout incorrect type (%d, 2)", j),
                        gridComps[i * 2 + j], instanceOf(JButton.class));
            }

        }
    }

    @Test
    public void testCardSizeInvalid() {
        // Constants
        Dimension boardDim = new Dimension(5, 5);
        CallbackStub cbs = new CallbackStub();

        try {
            // Initialise with a bad move count.
            SwingRenderer renderer = new SwingRenderer(cbs, boardDim, 0);
            fail("Didn't throw error with invalid move count.");
        }
        catch (AssertionError e) {
            // Pass.
        }
    }

    class CallbackStub implements CallbackConsumer {
        // Was something clicked?
        private boolean squareClicked;
        private boolean moveClicked;

        // Where was it clicked?
        private int moveIdx;
        private int squareX;
        private int squareY;

        CallbackStub() {
            reset();
        }

        /**
         * Reset all values to beginning state.
         */
        public void reset() {
            squareClicked = false;
            moveClicked = false;
            moveIdx = -1;
            squareX = -1;
            squareY = -1;
        }

        @Override
        public void onSquareClicked(int x, int y) {
            squareClicked = true;
            squareX = x;
            squareY = y;
        }

        @Override
        public void onMoveClicked(int idx) {
            moveClicked = true;
            moveIdx = idx;
        }


    }

}
