import java.util.stream.IntStream

interface Fish : AquariumObject {

    val movesLeft: Int

    companion object {
        val MAXIMUM_CAPACITY = 5
    }

    @Eg(construct = arrayOf("new Point(1, 1)"),
            given = arrayOf("new Aquarium(5, 5), 1"),
            returns = "new Point(0, 1)")
    fun nextPositionRaw(randomInt: Int): Point {
        return when (randomInt % 4) {
            0 -> Point(position.x, position.y + 1)
            1 -> Point(position.x - 1, position.y)
            2 -> Point(position.x, position.y - 1)
            else -> Point(position.x + 1, position.y)
        }
    }

    @Eg(construct = arrayOf("new Point(1, 1)"),
            given = arrayOf("new Aquarium(5, 5), 1"),
            returns = "new Point(0, 1)")
    fun nextPosition(aq: Aquarium, randomInt: Int): Point {
        return aq.wrap(nextPositionRaw(randomInt))
    }
}
