import kotlin.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FishTest {

    @Test
    public void nextposition() throws Exception {
        assertEquals("nextPosition(0)", new Pair(0, 1), new Fish(0, new Pair(0,0)).nextPosition(0));
        assertEquals("nextPosition(3)", new Pair(1, 0), new Fish(0, new Pair(0,0)).nextPosition(3));
    }

}
