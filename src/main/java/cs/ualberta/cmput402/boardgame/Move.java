package cs.ualberta.cmput402.boardgame;

import cs.ualberta.cmput402.boardgame.Offsets;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Move {
    //formatted L -> R appearance of possible squares
    DRAGON(new Offsets[] {new Offsets(-2,1), new Offsets(-1,-1), new Offsets(2,1),
			  new Offsets(1,-1)}),
    
    GOOSE(new Offsets[] {new Offsets(-1,1), new Offsets(-1,0), new Offsets(1,0),
			 new Offsets(1,-1)}),

    ELEPHANT(new Offsets[] {new Offsets(-1,1), new Offsets(-1,0), new Offsets(1,0),
			    new Offsets(1,1)}),

    MONKEY(new Offsets[] {new Offsets(-1,1), new Offsets(-1,-1), new Offsets(1,1),
			  new Offsets(1,-1)}),

    TIGER(new Offsets[] {new Offsets(0, 2), new Offsets(0, -1)});
    
    private boolean selected;
    private Offsets[] offsets;
    public final Image icon;
    
    Move(Offsets[] offsets){
	    selected = false;
	    this.offsets = offsets;
	    icon = createIcon(offsets);
    }

    public void select(){
	selected = true;
    }
    
    public boolean isChosen(){
	return selected;
    }

    public Offsets[] getOffsets(){
	return offsets;
    }

    private static Image createIcon(Offsets[] offsets) {
        // TODO: Can we get these values programmatically? A static function perhaps?
        int imgSize = 64;
        // Make our image.
        BufferedImage img = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG = img.createGraphics();

        // Set background color.
        imgG.setColor(Color.WHITE);
        imgG.fillRect(0, 0, imgSize, imgSize);

        // Find maximum offset to determine the size of the grid.
        int maxOffset = 0;
        for (Offsets off : offsets) {
            maxOffset = Math.max(maxOffset, Math.max(Math.abs(off.xOffset), Math.abs(off.yOffset)));
        }

        // Draw lines.
        int gridSize = maxOffset * 2 + 1; // Negative/positive offset from center cell.
        float delta = (float) imgSize / gridSize;
        imgG.setColor(Color.BLACK);
        for (int i = 0; i < gridSize; ++i) {
            int offset = (int) (delta * i); // Floating op casted to int minimises floating error.
            imgG.drawLine(0, offset, imgSize - 1, offset); // Horizontal.
            imgG.drawLine(offset, 0, offset, imgSize - 1); // Vertical.
        }

        // Bottom and right lines.
        imgG.drawLine(0, imgSize - 1, imgSize - 1,  imgSize - 1); // Horizontal.
        imgG.drawLine(imgSize - 1, 0, imgSize - 1, imgSize); // Vertical.

        // Calculate circle widths. More conversion from float to int to avoid error
        float circWidth = ((delta * 2) / 3); // 4/5 of the width of a cell.
        int circInset = (int) ((delta - circWidth) / 2); // Divide the rest of the width up to give even margins.

        // Draw center circle representing your piece.
        int centerCorner = (int) (maxOffset * delta + circInset);
        imgG.setColor(Color.DARK_GRAY);
        imgG.fillOval(centerCorner, centerCorner, (int) circWidth, (int) circWidth);

        // Draw destination offsets.
        for (Offsets off : offsets) {
            // Calculate corner positions.
            int xCorner = (int) ((off.xOffset + maxOffset) * delta + circInset);
            int yCorner = (int) ((off.yOffset + maxOffset) * delta + circInset);

            // Draw actual circle.
            imgG.setColor(Color.GREEN);
            imgG.drawOval(xCorner, yCorner, (int) circWidth, (int) circWidth);
        }

        return img;
    }
}
