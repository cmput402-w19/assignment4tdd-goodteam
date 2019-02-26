package cs.ualberta.cmput402.boardgame.board;

import cs.ualberta.cmput402.boardgame.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Piece{
    RED_STUDENT(Player.Team.RED, false),
    RED_MASTER(Player.Team.RED, true),
    BLUE_STUDENT(Player.Team.BLUE, false),
    BLUE_MASTER(Player.Team.BLUE, true);

    private Player.Team team;
    private boolean isMaster;
    private final Image icon;
    private final int iconSize;

    Piece(Player.Team team, boolean master) {
        this.team = team;
        isMaster = master;
        iconSize = 64;
        icon = createIcon();
    }

    public boolean isMaster() {
        return isMaster;
    }

    public Player.Team getTeam() {
        return team;
    }

    public Image getIcon() {
        return icon;
    }

    private Image createIcon() {
        // Make our image.
        BufferedImage img = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imgG = img.createGraphics();

        // Set background color.
        Color clear = new Color(1.f, 1.f, 1.f, 0);
        imgG.setColor(clear); // Clear.
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
            imgG.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            imgG.setColor(clear);
            imgG.fillOval(iconCenter - innerRadius, iconCenter - innerRadius,
                    innerRadius * 2, innerRadius * 2);
        } else {
            // Math setup.
            int radius = iconSize / 4;

            // Draw circle.
            imgG.fillOval(iconCenter - radius, iconCenter - radius,
                    radius * 2, radius * 2);
        }
        return img;
    }
}
