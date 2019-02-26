import cs.ualberta.cmput402.boardgame.Offsets;
import cs.ualberta.cmput402.boardgame.Move;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by knewbury on 02-23-19.                                                         
 */
public class MoveTest {

    private Move dragonMove;
    private Move gooseMove;
    private Move elephantMove;
    private Move monkeyMove;
    private Move tigerMove;
    private Offsets offsets1, offsets2, offsets3;

    @Before
    public void setup() {
        dragonMove = Move.DRAGON;
        gooseMove = Move.GOOSE;
        elephantMove = Move.ELEPHANT;
        monkeyMove = Move.MONKEY;
        tigerMove = Move.TIGER;
        offsets1 = new Offsets(-1, 1);
        offsets2 = new Offsets(-2, 1);
        offsets3 = new Offsets(0, 2);
    }

    @Test
    public void testGetOffsetsDragonMove() {
        assert(dragonMove.getOffsets()[0].equals(offsets2));
    }

    @Test
    public void testGetOffsetsGooseMove() {
        assert(gooseMove.getOffsets()[0].equals(offsets1));
    }

    @Test
    public void testGetOffsetsElephantMove() {
        assert(elephantMove.getOffsets()[0].equals(offsets1));
    }

    @Test
    public void testGetOffsetsMonkeyMove() {
        assert(monkeyMove.getOffsets()[0].equals(offsets1));
    }

    @Test
    public void testGetOffsetsTigerMove() {
        assert(tigerMove.getOffsets()[0].equals(offsets3));
    }
    
    @Test
    public void testSelectChosen() {
        assert(dragonMove.isChosen() == false);
        dragonMove.select();
        assert(dragonMove.isChosen() == true);
    }

    @Test
    public void testIconCreation() {
        // A bit of reflection trickery to make a private function available.
        try {
            Method createIcon = Move.class.getDeclaredMethod("createIcon", Offsets[].class);

            // Make sure it's private before we set it as accessible.
            assertTrue("Move.createIcon was not private.", Modifier.isPrivate(createIcon.getModifiers()));
            createIcon.setAccessible(true);

            // Get result and make sure it's the right type.
            Object res = createIcon.invoke(null, (Object) new Offsets[0]);
            assertThat(res, instanceOf(BufferedImage.class));
            BufferedImage img = (BufferedImage) res;

            // Acceptance testing.
            // Icon size.
            int expectedSize = 64;
            assertEquals("Move icon width was incorrect.", expectedSize, img.getWidth());
            assertEquals("Move icon height was incorrect.", expectedSize, img.getHeight());

            // Some specific pixels.
            assertEquals("Top left of icon was not black.",
                    Color.BLACK.getRGB(), img.getRGB(0,0));
            assertEquals("Top right of icon was not black.",
                    Color.BLACK.getRGB(), img.getRGB(expectedSize - 1,0));
            assertEquals("Bottom left of icon was not black.",
                    Color.BLACK.getRGB(), img.getRGB(0,expectedSize - 1));
            assertEquals("Bottom right of icon was not black.",
                    Color.BLACK.getRGB(), img.getRGB(expectedSize - 1,expectedSize - 1));
            assertEquals("Center of icon was not black.",
                    Color.DARK_GRAY.getRGB(), img.getRGB(expectedSize / 2,expectedSize / 2));

        }
        catch (NoSuchMethodException e) {
            fail("Move did not have icon creation method.");
        }
        catch (IllegalAccessException e) {
            fail("Move.createIcon was illegally accessed.");
        }
        catch (InvocationTargetException e) {
            fail("Move.createIcon was invoked with a bad target.");
        }
    }
}
