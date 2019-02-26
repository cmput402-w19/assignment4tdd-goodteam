import cs.ualberta.cmput402.boardgame.Player;
import cs.ualberta.cmput402.boardgame.board.Piece;

import org.junit.Test;
import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

/**                                                                           
 * Created by knewbury on 02-22-19                                             
 * This one is a bit overboard I'll admit
 */

public class PieceTest {

    private Piece piece;

    @Test
    public void testBlueStudentPiece(){
        piece = Piece.BLUE_STUDENT;
        assert(!piece.isMaster());
        assert(piece.getTeam().equals(Player.Team.BLUE));
    }

    @Test
    public void testRedMasterPiece(){
        piece = Piece.RED_MASTER;
        assert(piece.isMaster());
        assert(piece.getTeam().equals(Player.Team.RED));
    }

    @Test
    public void testRedStudentPiece(){
        Piece piece = Piece.RED_STUDENT;
        assert(!piece.isMaster());
        assert(piece.getTeam().equals(Player.Team.RED));
    }

    @Test
    public void testBlueMasterPiece() {
        piece = Piece.BLUE_MASTER;
        assert (piece.isMaster());
        assert (piece.getTeam().equals(Player.Team.BLUE));
    }

    @Test
    public void testMasterIcon() {
        // A bit of reflection trickery to make a private function available.
        try {
            // Get the createIcon method.
            Method createIcon = Piece.class.getDeclaredMethod("createIcon");

            // Make sure it's private before we set it as accessible.
            assertTrue("Piece.createIcon was not private.", Modifier.isPrivate(createIcon.getModifiers()));
            createIcon.setAccessible(true);

            // The pieces we're investigating.
            Piece[] pieces = new Piece[] { Piece.BLUE_MASTER, Piece.RED_MASTER };

            for (Piece piece : pieces) {
                // Get our expected size.
                Field iconSizeField = Piece.class.getDeclaredField("iconSize");

                // Check accessibility.
                assertTrue("Piece.iconSize was not private.", Modifier.isPrivate(iconSizeField.getModifiers()));
                iconSizeField.setAccessible(true);

                // Check that the field is an integer.
                assertEquals("Piece.iconSize was not int.", Integer.TYPE, iconSizeField.getType());
                int expectedSize = iconSizeField.getInt(piece);

                // Get result and make sure it's the right type.
                Object res = createIcon.invoke(piece);
                assertThat("Master piece icon was not a BufferedImage.", res, instanceOf(BufferedImage.class));
                BufferedImage img = (BufferedImage) res;

                // Acceptance testing.
                // Icon size.
                assertEquals("Master piece icon width was incorrect.", expectedSize, img.getWidth());
                assertEquals("Master piece icon height was incorrect.", expectedSize, img.getHeight());

                // Math setup for point checking.
                int beginEdge = 0;
                int endEdge = expectedSize - 1;
                int center = expectedSize / 2;
                int beginCardinal = expectedSize / 4;
                int endCardinal = (expectedSize * 3) / 4;

                // Check corners and center are white.
                Color clear = new Color(0, 0, 0, 0);
                assertEquals("Top left of icon was not clear.",
                        clear.getRGB(), img.getRGB(beginEdge, beginEdge));
                assertEquals("Top right of icon was not clear.",
                        clear.getRGB(), img.getRGB(endEdge, beginEdge));
                assertEquals("Bottom left of icon was not clear.",
                        clear.getRGB(), img.getRGB(beginEdge, endEdge));
                assertEquals("Bottom right of icon was not clear.",
                        clear.getRGB(), img.getRGB(endEdge, endEdge));
                assertEquals("Center of icon was not clear.",
                        clear.getRGB(), img.getRGB(center, center));

                // Check the cardinal direction quarters.
                assertEquals("West quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(beginCardinal, center));
                assertEquals("North quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(center, beginCardinal));
                assertEquals("East quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(endCardinal, center));
                assertEquals("South quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(center, endCardinal));

                // Use get icon.
                Image otherImage = piece.getIcon();
                assertThat("Piece getIcon was not a BufferedImage.",
                        otherImage, instanceOf(BufferedImage.class));
                BufferedImage oImg = (BufferedImage) otherImage;

                // Check dimensions
                assertEquals("Generated and gotten icons were not equal widths.",
                        img.getWidth(), oImg.getWidth());
                assertEquals("Generated and gotten icons were not equal heights.",
                        img.getHeight(), oImg.getHeight());

                // They should be identical so random selections are ok.
                Random rand = new Random();
                for (int i = 0; i < 10; ++i) {
                    int x = rand.nextInt(img.getWidth());
                    int y = rand.nextInt(img.getHeight());
                    assertEquals(
                            String.format("Created and getIcon versions are pixelwise different at (%d, %d)", x, y),
                            img.getRGB(x, y), oImg.getRGB(x, y
                            ));
                }
            }
        }
        catch (NoSuchFieldException e) {
            fail("Piece did not have an icon size field.");
        }
        catch (NoSuchMethodException e) {
            fail("Piece did not have icon creation method.");
        }
        catch (IllegalAccessException e) {
            fail("Piece.createIcon was illegally accessed.");
        }
        catch (InvocationTargetException e) {
            fail("Piece.createIcon was invoked with a bad target.");
        }
    }

    @Test
    public void testStudentIcon() {
        // A bit of reflection trickery to make a private function available.
        try {
            // Get the createIcon method.
            Method createIcon = Piece.class.getDeclaredMethod("createIcon");

            // Make sure it's private before we set it as accessible.
            assertTrue("Piece.createIcon was not private.", Modifier.isPrivate(createIcon.getModifiers()));
            createIcon.setAccessible(true);

            // The pieces we're investigating.
            Piece[] pieces = new Piece[] { Piece.BLUE_STUDENT, Piece.RED_STUDENT};

            for (Piece piece : pieces) {
                // Get our expected size.
                Field iconSizeField = Piece.class.getDeclaredField("iconSize");

                // Check accessibility.
                assertTrue("Piece.iconSize was not private.", Modifier.isPrivate(iconSizeField.getModifiers()));
                iconSizeField.setAccessible(true);

                // Check that the field is an integer.
                assertEquals("Piece.iconSize was not int.", Integer.TYPE, iconSizeField.getType());
                int expectedSize = iconSizeField.getInt(piece);

                // Get result and make sure it's the right type.
                Object res = createIcon.invoke(piece);
                assertThat("Student piece icon was not a BufferedImage.", res, instanceOf(BufferedImage.class));
                BufferedImage img = (BufferedImage) res;

                // Acceptance testing.
                // Icon size.
                assertEquals("Student piece icon width was incorrect.", expectedSize, img.getWidth());
                assertEquals("Student piece icon height was incorrect.", expectedSize, img.getHeight());

                // Math setup for point checking.
                int beginEdge = 0;
                int endEdge = expectedSize - 1;
                int center = expectedSize / 2;
                int beginCardinal = (3 * expectedSize) / 8;
                int endCardinal = (expectedSize * 5) / 8;

                // Check corners are clear.
                Color clear = new Color(0, 0, 0, 0);
                assertEquals("Top left of icon was not clear.",
                        clear.getRGB(), img.getRGB(beginEdge, beginEdge));
                assertEquals("Top right of icon was not clear.",
                        clear.getRGB(), img.getRGB(endEdge, beginEdge));
                assertEquals("Bottom left of icon was not clear.",
                        clear.getRGB(), img.getRGB(beginEdge, endEdge));
                assertEquals("Bottom right of icon was not clear.",
                        clear.getRGB(), img.getRGB(endEdge, endEdge));

                // Check center and the cardinal direction quarters are team color.
                assertEquals("Center of icon was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(center, center));
                assertEquals("West quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(beginCardinal, center));
                assertEquals("North quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(center, beginCardinal));
                assertEquals("East quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(endCardinal, center));
                assertEquals("South quarter was not team color.",
                        piece.getTeam().getColor().getRGB(), img.getRGB(center, endCardinal));

                // Use get icon.
                Image otherImage = piece.getIcon();
                assertThat("Piece getIcon was not a BufferedImage.",
                        otherImage, instanceOf(BufferedImage.class));
                BufferedImage oImg = (BufferedImage) otherImage;

                // Check dimensions
                assertEquals("Generated and gotten icons were not equal widths.",
                        img.getWidth(), oImg.getWidth());
                assertEquals("Generated and gotten icons were not equal heights.",
                        img.getHeight(), oImg.getHeight());

                // They should be identical so random selections are ok.
                Random rand = new Random();
                for (int i = 0; i < 10; ++i) {
                    int x = rand.nextInt(img.getWidth());
                    int y = rand.nextInt(img.getHeight());
                    assertEquals(
                            String.format("Created and getIcon versions are pixelwise different at (%d, %d)", x, y),
                            img.getRGB(x, y), oImg.getRGB(x, y
                            ));
                }
            }
        }
        catch (NoSuchFieldException e) {
            fail("Piece did not have an icon size field.");
        }
        catch (NoSuchMethodException e) {
            fail("Piece did not have icon creation method.");
        }
        catch (IllegalAccessException e) {
            fail("Piece.createIcon was illegally accessed.");
        }
        catch (InvocationTargetException e) {
            fail("Piece.createIcon was invoked with a bad target.");
        }
    }
}
