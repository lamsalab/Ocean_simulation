import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class PlantTest {

    @Test
    public void update() throws Exception {
        Plant testPlant = new Plant(new Point(0,0));

        Set<AquariumObject> plantSet = new HashSet<>(1);
        plantSet.add(new Plant(new Point(1, 0)));

        Set<AquariumObject> plantAndFishSet = new HashSet<>(2);
        plantAndFishSet.add(new Plant (new Point(1,0)));
        plantAndFishSet.add(new Minnow(new Point(1, 1), 5));

        Aquarium aquarium = new Aquarium(10, 10);

        assertEquals("PlantUpdate({}, 0)",
                new UpdateResult(Collections.emptySet(), testPlant, new Plant(new Point(0,0))),
                testPlant.update(Collections.emptySet(), 0, aquarium));
        assertEquals("PlantUpdate({Plant(1,0)}, 0)",
                new UpdateResult(Collections.emptySet(), testPlant, new Plant(new Point(0,0))),
                testPlant.update(Collections.emptySet(), 0, aquarium));
        assertEquals("PlantUpdate({Plant(1,0), Fish(1,1)}, 0)",
                new UpdateResult(Collections.emptySet(), testPlant, new Plant(new Point(0,0))),
                testPlant.update(Collections.emptySet(), 0, aquarium));
    }

}
