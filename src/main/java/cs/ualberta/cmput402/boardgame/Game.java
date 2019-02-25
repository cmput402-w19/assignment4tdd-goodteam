package cs.ualberta.cmput402.boardgame;

import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.fsm.CallbackConsumer;
import cs.ualberta.cmput402.boardgame.rendering.SwingRenderer;

import javax.swing.*;
import java.awt.*;

public class Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIThread());
    }

    private static class GUIThread implements Runnable {
        @Override
        public void run() {
            // TODO: Clean up when pulling in board changes.
            Board board = new Board();

            // TODO This is a stub.
            CallbackConsumer cbc = new CallbackConsumer() {
                @Override
                public void onSquareClicked(int x, int y) {
                    System.out.println("Square!");
                }

                @Override
                public void onMoveClicked(int idx) {
                    System.out.println("Card!");
                }
            };

            // Create renderer.
            // TODO: Use actual board dimensions for width/height
            int boardSize = board.getSize();
            int reserveMoves = 2;
            SwingRenderer renderer = new SwingRenderer(cbc, new Dimension(boardSize, boardSize), reserveMoves);

            // Build GUI window.
            // Based on https://stackoverflow.com/a/21142687/2379240
            JFrame f = new JFrame("Onitama");

            // Set window parameters.
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true); // See https://stackoverflow.com/a/7143398/418556 for demo.

            // Set up panel that contains individual GUIs.
            JPanel guis = new JPanel(new GridLayout(1, 2)); // Two internal panels, side by side.
            f.add(guis);

            // Add our internal guis.
            guis.add(renderer.getGUI());
            guis.add(renderer.getMoveGui());

            // Pack the filled guis and fix the size.
            f.pack();
            f.setMinimumSize(f.getSize());
            f.setResizable(false);

            // Make visible.
            f.setVisible(true);
        }
    }
}
