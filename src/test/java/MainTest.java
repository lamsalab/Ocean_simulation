import org.junit.Test;

import java.util.Arrays;

public class MainTest {
    @Test
    public void testReturnsUpdateObjects() throws Exception {
        org.junit.Assert.assertEquals("updateObjects(new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5)))", new java.util.ArrayList(Arrays.asList(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 2), 4))), Main.updateObjects(new java.util.ArrayList(Arrays.asList(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5)))));
        org.junit.Assert.assertEquals("updateObjects(new java.util.ArrayList().add(new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 1), 5)))", new java.util.ArrayList(Arrays.asList(new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 2), 4))), Main.updateObjects(new java.util.ArrayList(Arrays.asList(new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 1), 5)))));

    }

}
