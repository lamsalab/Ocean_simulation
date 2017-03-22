import kotlin.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FishTest {

    @Test
    public void nextposition() throws Exception {
        assertEquals("nextPosition(0)", new Point(0, 1), new Fish(0, new Point(0,0)).nextPosition(0));
        assertEquals("nextPosition(3)", new Point(1, 0), new Fish(0, new Point(0,0)).nextPosition(3));
        assertEquals("nextPosition(100)", new Point(0, 1), new Fish(0, new Point(0,0)).nextPosition(100));
    }

    @Test
    public void update() throws Exception {
        assertEquals("update({}, 0", UpdateResult());
    }

}
