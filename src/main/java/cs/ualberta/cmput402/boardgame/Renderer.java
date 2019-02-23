package cs.ualberta.cmput402.boardgame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


// Initial ideas based on https://stackoverflow.com/a/21142687/2379240
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
    private void createTileGraphics() {
        // Create the empty tile.
        BufferedImage empty = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D emptyG = empty.createGraphics();
        emptyG.setColor(Color.WHITE);
        emptyG.fillRect(0, 0, tileSize, tileSize);
        this.empty = empty;

        // Iterate over teams and piece types.
        int tileCenter = tileSize / 2;
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
                        // Math setup.
                        int outerRadius = tileSize / 3;
                        int innerRadius = tileSize / 5;

                        // Draw king.
                        imgG.setColor(team.color);
                        imgG.fillOval(tileCenter - outerRadius, tileCenter - outerRadius,
                                outerRadius * 2, outerRadius * 2);
                        imgG.setColor(Color.WHITE);
                        imgG.fillOval(tileCenter - innerRadius, tileCenter - innerRadius,
                                innerRadius * 2, innerRadius * 2);
                        break;
                    case PAWN:
                        // Math setup.
                        int radius = tileSize / 4;

                        // Draw pawn.
                        imgG.setColor(team.color);
                        imgG.fillOval(tileCenter - radius, tileCenter - radius,
                                radius * 2, radius * 2);
                        break;
                }

                // Save the image.
                pieces[team.idx][piece.idx] = img;
            }
        }
    }
}
