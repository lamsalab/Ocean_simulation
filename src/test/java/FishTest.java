
import org.junit.Test;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FishTest {

    @Test
    public void nextposition() throws Exception {

        Aquarium aquarium = new Aquarium(10, 10);

        assertEquals("nextPosition(0)", new Point(0, 1), new Minnow(new Point(0,0), 5).nextPosition(aquarium,0));
        assertEquals("nextPosition(3)", new Point(1, 0), new Minnow(new Point(0,0), 5).nextPosition(aquarium, 3));
        assertEquals("nextPosition(100)", new Point(0, 1), new Minnow(new Point(0,0), 5).nextPosition(aquarium, 100));
        assertEquals("nextPosition(5)", new Point(-1, 0), new Shark(new Point(0,0), 5).nextPosition(aquarium, 5));
    }
}
