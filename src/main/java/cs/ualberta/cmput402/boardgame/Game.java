package cs.ualberta.cmput402.boardgame;

import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.rendering.SwingRenderer;

import javax.swing.*;
import java.awt.*;

public class Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIThread());
        System.out.println("Hello");
    }

    private static class GUIThread implements Runnable {
        @Override
        public void run() {
            // TODO: Clean up when pulling in board changes.
            Board board = new Board();

            // Create renderer.
            // TODO: Use actual board dimensions for width/height
            SwingRenderer renderer = new SwingRenderer(new Dimension(5, 5));

            // Build GUI window.
            // Based on https://stackoverflow.com/a/21142687/2379240
            JFrame f = new JFrame("Onitama");

            // Set window parameters.
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true); // See https://stackoverflow.com/a/7143398/418556 for demo.
            f.setMinimumSize(f.getSize());

            // Add our renderer and lock the size.
            f.add(renderer.getGUI());
            f.pack();
            f.setResizable(false);

            // Make visible.
            f.setVisible(true);
        }
    }
}
