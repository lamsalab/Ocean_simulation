import java.util.stream.IntStream

open class Fish (override val movesLeft: Int, override val position: Point) : AquariumObject {


    companion object {
        val MAXIMUM_CAPACITY = 5
    }

    override fun nextPosition(randomInt: Int): Point {
        return when (randomInt.mod(4)) {
            0 -> Point(position.x, position.y + 1)
            1 -> Point(position.x - 1, position.y)
            2 -> Point(position.x, position.y - 1)
            else -> Point(position.x + 1, position.y)
        }
    }

    override fun update(objects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult {
        var killSet: Set<AquariumObject> = setOf()

        for (obj in objects) {
            if (obj.position == this.position) {
                if (obj is Plant) {
                    killSet.plus(obj)
                }
            }
        }

        return UpdateResult(killSet, this,
                if (killSet.isEmpty())
                    Fish(movesLeft - 1, nextPosition(randomIntStream.findFirst().asInt))
                else
                    Fish(MAXIMUM_CAPACITY, nextPosition(randomIntStream.findFirst().asInt)))
    }
}
