package cs.ualberta.cmput402.boardgame.rendering;

import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.fsm.CallbackConsumer;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


// Initial ideas based on https://stackoverflow.com/a/21142687/2379240
public class SwingRenderer implements GameRenderer {

    // GUI elements.
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] squares;

    // Move GUI elements.
    private final JPanel moveGui = new JPanel(new BorderLayout(3, 3));
    private JButton[] theirMoves;
    private JButton[] myMoves;
    private JButton neutralMove;

    // Assets.
    private Image[][] pieces = new Image[2][2];
    private Image empty;

    // Dimensions.
    private final int tileSize = 64;

    // For indexing the piece array.
    // TODO: Tear these enums out in favour of other enums.
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

    /**
     * Initialises the swing renderer with a place to send callbacks, the size of the board, and how many moves a player
     * holds at once.
     * @param callback The callback destination.
     * @param boardDim The board dimensions.
     * @param moveCount The player move count.
     */
    public SwingRenderer(CallbackConsumer callback, Dimension boardDim, int moveCount) {
        initGUI(boardDim);
        initMoveGUI(moveCount);
    }

    public JPanel getGUI() {
        return gui;
    }

    public JPanel getMoveGui() {
        return moveGui;
    }

    /**
     * Builds the GUI from swing components.
     *
     * @param dim The dimensions of the board (number of tiles).
     */
    private void initGUI(Dimension dim) {
        // Create assets.
        createTileGraphics();

        // Set up main GUI.
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Start setting up UI.
        JPanel board = new TiledPanel(dim);

        // Create a border
        board.setBorder(new CompoundBorder(
                new EmptyBorder(4,4,4,4),
                new LineBorder(Color.BLACK, 3, true)
        ));

        // Build square buttons.
        squares = new JButton[dim.height][dim.width];
        Insets buttonInset = new Insets(0, 0, 0,0); // No insets.
        for (int i = 0; i < dim.height; ++i) {
            for (int j = 0; j < dim.width; ++j) {
                JButton button = new JButton();

                // Remove insets
                button.setMargin(buttonInset);

                // Set appearance.
                ImageIcon icon = new ImageIcon(empty);
                button.setIcon(icon);
                button.setBackground(Color.WHITE);

                // Save reference to the button and add to the layout.
                squares[i][j] = button;
                board.add(button);
            }
        }

        JPanel boardConstraints = new JPanel(new GridBagLayout());
        boardConstraints.add(board);
        gui.add(boardConstraints);
    }

    private void initMoveGUI(int reserveMoves) {
        // Sanity check.
        assert(reserveMoves > 0);

        // Make our card gui dimensions. It reserveMoves in width.
        int height = 3; // Always three slots vertically: yours, exchange, mine.
        Dimension guiDims = new Dimension(reserveMoves, height);

        // Set up main GUI.
        moveGui.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Start setting up UI.
        JPanel moveGrid = new TiledPanel(guiDims);

        // Create a border
        moveGrid.setBorder(new CompoundBorder(
                new EmptyBorder(4,4,4,4),
                new LineBorder(Color.BLACK, 3, true)
        ));

        JPanel moveConstraints = new JPanel(new GridBagLayout());
        moveConstraints.add(moveGrid);
        moveGui.add(moveConstraints);
    }

    @Override
    public void drawBoard(Board board) {

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

    private class TiledPanel extends JPanel {

        /**
         * Call super constructor with gridlayout using dimension width/height.
         * @param dimension The dimension to use.
         */
        TiledPanel(Dimension dimension) {
            super(new GridLayout(dimension.height, dimension.width));
        }

        /**
         * Override the preferred size to return the largest it can, in
         * a square shape.  Must (must, must) be added to a GridBagLayout
         * as the only component (it uses the parent as a guide to size)
         * with no GridBagConstaint (so it is centered).
         */
        @Override
        public final Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            Dimension prefSize = null;
            Component c = getParent();
            if (c == null) {
                prefSize = d;
            } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                prefSize = c.getSize();
            } else {
                prefSize = d;
            }
            int w = (int) prefSize.getWidth();
            int h = (int) prefSize.getHeight();
            // the smaller of the two sizes
            int s = (w>h ? h : w);
            return new Dimension(s,s);
        }
    }
}
