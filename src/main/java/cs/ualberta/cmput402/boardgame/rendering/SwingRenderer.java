package cs.ualberta.cmput402.boardgame.rendering;

import cs.ualberta.cmput402.boardgame.Move;
import cs.ualberta.cmput402.boardgame.board.Board;
import cs.ualberta.cmput402.boardgame.fsm.CallbackConsumer;
import cs.ualberta.cmput402.boardgame.fsm.SquareClickCallback;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


// Initial ideas based on https://stackoverflow.com/a/21142687/2379240
public class SwingRenderer implements GameRenderer {

    // GUI elements.
    private final JPanel boardGui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] squares;

    // Move GUI elements.
    private final JPanel moveGui = new JPanel(new BorderLayout(3, 3));
    private JButton[] theirMoves;
    private JButton[] myMoves;
    private JLabel neutralMove;

    // Assets.
    private Image empty;

    // Dimensions.
    private final int tileSize = 64;

    // Callback handler.
    CallbackConsumer callback;

    /**
     * Initialises the swing renderer with a place to send callbacks, the size of the board, and how many moves a player
     * holds at once.
     * @param callback The callback destination.
     * @param boardDim The board dimensions.
     * @param moveCount The player move count.
     */
    public SwingRenderer(CallbackConsumer callback, Dimension boardDim, int moveCount) {
        // Save callback handler.
        this.callback = callback;

        // Create general assets.
        createBackgroundGraphics();

        // Create GUIs.
        initBoardGUI(boardDim);
        initMoveGUI(moveCount);
    }

    public JPanel getBoardGui() {
        return boardGui;
    }

    public JPanel getMoveGui() {
        return moveGui;
    }

    /**
     * Builds the GUI from swing components.
     *
     * @param dim The dimensions of the board (number of tiles).
     */
    private void initBoardGUI(Dimension dim) {
        // Set up main GUI.
        boardGui.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Start setting up UI.
        JPanel board = new JPanel(new GridLayout(dim.height, dim.width));

        // Create a border
        board.setBorder(new CompoundBorder(
                new EmptyBorder(4,4,4,4),
                new LineBorder(Color.BLACK, 3, true)
        ));

        // Build square buttons.
        squares = new JButton[dim.height][dim.width];
        Insets buttonInset = new Insets(0, 0, 0,0); // No insets.
        for (int y = 0; y < dim.height; ++y) {
            for (int x = 0; x < dim.width; ++x) {
                // Get button, save a reference, and add to the layout.
                JButton button = constructButton();
                button.addActionListener(new SquareClickCallback(callback, x, y));
                squares[y][x] = button;
                board.add(button);
            }
        }

        // Surround the board in constraints to allow nicer layouts.
        JPanel boardConstraints = new JPanel(new GridBagLayout());
        boardConstraints.add(board);
        boardGui.add(boardConstraints);
    }

    /**
     * Builds the move GUI from swing components.
     *
     * @param moveCount The number of cards each player holds.
     */
    private void initMoveGUI(int moveCount) {
        // Sanity check.
        assert(moveCount > 0);

        // Make our move gui dimensions. It is moveCount in width.
        int height = 3; // Always three slots vertically: yours, exchange, mine.
        Dimension guiDims = new Dimension(moveCount, height);

        // Set up main GUI.
        moveGui.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Start setting up UI.
        JPanel moveGrid = new JPanel(new GridLayout(guiDims.height, guiDims.width));

        // Create a border
        moveGrid.setBorder(new CompoundBorder(
                new EmptyBorder(4,4,4,4),
                new LineBorder(Color.BLACK, 3, true)
        ));

        // Build top row buttons
        theirMoves = new JButton[moveCount];
        for (int i = 0; i < moveCount; ++i) {
            // Get button, save a reference, and add to the layout.
            JButton button = constructButton();
            theirMoves[i] = button;
            moveGrid.add(button);
        }

        // Build middle row.
        // First is the exchange move, contained in a label.
        {
            // First column.
            JLabel label = new JLabel(new ImageIcon(empty));
            neutralMove = label;
            moveGrid.add(label);
        }
        // Rest are empty panels.
        for (int i = 1; i < moveCount; ++i) {
            moveGrid.add(new JPanel());
        }

        // Build bottom row buttons
        myMoves = new JButton[moveCount];
        for (int i = 0; i < moveCount; ++i) {
            // Get button, save a reference, and add to the layout.
            JButton button = constructButton();
            myMoves[i] = button;
            moveGrid.add(button);
        }

        // Finally, the panel constraints and the actual add.
        JPanel moveConstraints = new JPanel(new GridBagLayout());
        moveConstraints.add(moveGrid);
        moveGui.add(moveConstraints);
    }

    private JButton constructButton() {
        // New button.
        JButton button = new JButton();

        // Remove insets.
        Insets buttonInset = new Insets(0, 0, 0,0);
        button.setMargin(buttonInset);

        // Set the background.
        button.setBackground(Color.WHITE);

        // Set appearance.
        ImageIcon icon = new ImageIcon(empty);
        button.setIcon(icon);

        return button;
    }

    @Override
    public void drawBoard(Board board) {

    }

    @Override
    public void drawMoves(Move[] theirs, Move[] mine, Move extra) {

    }

    /**
     * Creates the images for the icons to use in the GUI.
     */
    private void createBackgroundGraphics() {
        // Create the empty tile.
        BufferedImage empty = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D emptyG = empty.createGraphics();
        emptyG.setColor(Color.WHITE);
        emptyG.fillRect(0, 0, tileSize, tileSize);
        this.empty = empty;
    }
}
