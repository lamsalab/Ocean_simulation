import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class SharkTest {
    @Test
    public void update() throws Exception {
        Shark testShark = new Shark(new Point(0, 0), 5);

        Set<AquariumObject> minnowSet = new HashSet<>(1);
        minnowSet.add(new Minnow(new Point(0, 0), 5));

        Set<AquariumObject> sharkAndFishSet = new HashSet<>(2);
        sharkAndFishSet.add(new Minnow(new Point(0, 0), 5));
        sharkAndFishSet.add(new Shark(new Point(1, 1), 5));

        assertEquals("SharkUpdate({}, 0)",
                new UpdateResult(Collections.emptySet(), testShark, new Shark(new Point(0, 1), 4)),
                testShark.update(Collections.emptySet(), IntStream.of(0)));
        assertEquals("SharkUpdate({Minnow(0,0)}, 0)",
                new UpdateResult(minnowSet, testShark, new Shark(new Point(0, 1), 5)),
                testShark.update(minnowSet, IntStream.of(0)));
        assertEquals("SharkUpdate({Minnow(0,0), Shark(1,1)}, 0)",
                new UpdateResult(minnowSet, testShark, new Shark(new Point(0, 1), 5)),
                testShark.update(sharkAndFishSet, IntStream.of(0)));
    }
}