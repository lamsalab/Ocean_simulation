import java.util.stream.IntStream

data class Fish (override var movesLeft: Int, override val position: Point) : AquariumObject {

    const val MAXIMUM_CAPACITY = 5

    override fun nextPosition(randomInt: Int): Point {
        return when (randomInt.mod(4)) {
            0 -> Point(position.x, position.y + 1)
            1 -> Point(position.x - 1, position.y)
            2 -> Point(position.x, position.y - 1)
            else -> Point(position.x + 1, position.y)
        }
    }

    override fun update(objects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult {


        return UpdateResult(, this, makeFutureSelf(randomIntStream))
    }

    fun makeFutureSelf(randomIntStream: IntStream): Fish {
        return Fish(movesLeft - 1, nextPosition(randomIntStream.findFirst().asInt))
    }

    fun refillHunger() {
        movesLeft +=
    }
}