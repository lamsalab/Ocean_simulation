import java.util.stream.IntStream

interface Fish : AquariumObject {

    val movesLeft: Int

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
}
