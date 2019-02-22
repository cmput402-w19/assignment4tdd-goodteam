package cs.ualberta.cmput402.boardgame;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import javax.swing.*;

public class Renderer {

    // GUI elements.
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    // Assets.
    private Image[][] pieces = new Image[2][2];
    private Image empty;

    // Dimensions.
    private final int tileSize = 64;

    // For indexing the piece array.
    private enum PieceType {
        PAWN(0),
        KING(1);

        // Piece type properties.
        public final int idx;

        PieceType(int idx) {
            this.idx = idx;
        }
    }
    private enum Team {
        RED(0, Color.RED),
        BLACK(1, Color.BLACK);

        // Team properties.
        public final int idx;
        public final Color color;

        Team(int idx, Color color) {
            this.idx = idx;
            this.color = color;
        }
    }

    Renderer() {
        initGUI();
    }

    private void initGUI() {
        createPieceImages();
    }

    public void drawBoard() {

    }

    /**
     * Creates the images for the icons to use in the GUI.
     */
    private void createPieceImages() {
        // Create the empty tile.
        BufferedImage empty = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D emptyG = empty.createGraphics();
        emptyG.setColor(Color.WHITE);
        emptyG.fillRect(0, 0, tileSize, tileSize)gi;

        // Iterate over teams and piece types.
        for (Team team : Team.values()) {
            for (PieceType piece : PieceType.values()) {
                // Instantiate a new image to modify.
                BufferedImage img = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
                Graphics2D imgG = img.createGraphics();

                // File BG with white.
                imgG.setColor(Color.WHITE);
                imgG.fillRect(0, 0, tileSize, tileSize);

                // Choose based on piece type.
                switch (piece) {
                    case KING:
                        // Draw king.
                        imgG.setColor(team.color);
                        imgG.fillOval(tileSize / 2, tileSize / 2, tileSize / 3, tileSize / 3);
                        imgG.setColor(Color.WHITE);
                        imgG.fillOval(tileSize / 2, tileSize / 2, tileSize / 5, tileSize / 5);
                        break;
                    case PAWN:
                        // Draw pawn.
                        imgG.setColor(team.color);
                        imgG.fillOval(tileSize / 2, tileSize / 2, tileSize / 4, tileSize / 4);
                        break;
                }

                // Save the image.
                pieces[team.idx][piece.idx] = img;
            }
        }
    }
}
