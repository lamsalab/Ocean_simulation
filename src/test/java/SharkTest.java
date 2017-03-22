import org.junit.Test;

import java.util.Collections;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class SharkTest {
    @Test
    public void update() throws Exception {
        assertEquals("update({}, 0)", null, new Shark(new Point(0, 0), 5).update(Collections.emptySet(), IntStream.of(0)));
    }
}
