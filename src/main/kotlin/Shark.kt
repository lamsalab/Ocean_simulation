import com.vocalabs.egtest.annotation.Eg
import java.util.stream.IntStream

data class Shark (override val position: Point, override val movesLeft: Int) : Fish{

    @Eg(construct = arrayOf("new Point(1, 1)", "5"),
            given = arrayOf("java.util.Collections.emptySet()", "0", "new Aquarium(5, 5)"),
            returns = "new UpdateResult(java.util.Collections.emptySet(), new Shark(new Point(1, 1), 5), new Shark(new Point(1, 2), 4))")
    @Eg(construct = arrayOf("new Point(1, 0)", "5"),
            given = arrayOf("java.util.Collections.emptySet()", "2", "new Aquarium(3, 3)"),
            returns = "new UpdateResult(java.util.Collections.emptySet(), new Shark(new Point(1, 0), 5), new Shark(new Point(1, 2), 4))")
    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult {
        var killSet: Set<AquariumObject> = setOf()

        for (obj in allObjects) {
            if (obj.position.equals(this.position)) {
                if (obj is Fish) {
                    killSet = killSet.plus(obj)
                }
            }
        }
        return UpdateResult(killSet, this,
                if (killSet.isEmpty())
                    Shark(nextPosition(aquarium, randomInt), movesLeft - 1)
                else
                    Shark(nextPosition(aquarium, randomInt), Fish.MAXIMUM_CAPACITY))
    }
}