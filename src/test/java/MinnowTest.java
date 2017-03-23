import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MinnowTest {
    @Test
    public void update() throws Exception {
        Minnow testMinnow = new Minnow(new Point(0, 0), 5);

        Set<AquariumObject> plantSet = new HashSet<>(1);
        plantSet.add(new Plant(new Point(0,0)));

        Set<AquariumObject> plantAndFishSet = new HashSet<>(2);
        plantAndFishSet.add(new Plant (new Point(0,0)));
        plantAndFishSet.add(new Minnow(new Point(1, 1), 5));

        Aquarium aquarium = new Aquarium(10, 10);

        assertEquals("MinnowUpdate({}, 0)",
                new UpdateResult(Collections.emptySet(), testMinnow, new Minnow(new Point(0,1), 4)),
                testMinnow.update(Collections.emptySet(), 0, aquarium));
        assertEquals("MinnowUpdate({Plant(0,0)}, 0)",
                new UpdateResult(plantSet, testMinnow, new Minnow(new Point(0,1), 5)),
                testMinnow.update(plantSet, 0, aquarium));
        assertEquals("MinnowUpdate({Plant(0,0), Fish(1,1)}, 0)",
                new UpdateResult(plantSet, testMinnow, new Minnow(new Point(0,1), 5)),
                testMinnow.update(plantAndFishSet, 0, aquarium));
    }
}
