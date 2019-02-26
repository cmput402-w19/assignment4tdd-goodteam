package cs.ualberta.cmput402.boardgame.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public enum Piece{
    RED_STUDENT(Board.Team.RED, false),
    RED_MASTER(Board.Team.RED, true),
    BLUE_STUDENT(Board.Team.BLUE, false),
    BLUE_MASTER(Board.Team.BLUE, true);

    private Board.Team team;
    private boolean isMaster;
    private final Image icon;
    private final int iconSize;

    Piece(Board.Team team, boolean master) {
        this.team = team;
        isMaster = master;
        iconSize = 64;
        icon = createIcon();
    }

    public boolean isMaster() {
        return isMaster;
    }

    public Board.Team getTeam() {
        return team;
    }

    public Image getIcon() {
        return icon;
    }

    private Image createIcon() {
        // Make our image.
        BufferedImage img = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG = img.createGraphics();

        // Set background color.
        imgG.setColor(Color.WHITE);
        imgG.fillRect(0, 0, iconSize, iconSize);

        // Set our initial color.
        imgG.setColor(team.getColor());

        // Necessary dimensions.
        int iconCenter = iconSize / 2;

        // Draw the actual icon.
        if (isMaster) {
            // Math setup.
            int outerRadius = iconSize / 3;
            int innerRadius = iconSize / 5;

            // Draw outer circle.
            imgG.fillOval(iconCenter - outerRadius, iconCenter - outerRadius,
                    outerRadius * 2, outerRadius * 2);

            // Remove inner
            imgG.setColor(Color.WHITE);
            imgG.fillOval(iconCenter - innerRadius, iconCenter - innerRadius,
                    innerRadius * 2, innerRadius * 2);
        } else {
            // Math setup.
            int radius = iconSize / 4;

            // Draw circle.
            imgG.setColor(team.getColor());
            imgG.fillOval(iconCenter - radius, iconCenter - radius,
                    radius * 2, radius * 2);
        }
        return img;
    }
}
