interface Fish : AquariumObject {

    val movesLeft: Int

    companion object {
        val MAXIMUM_CAPACITY = 5

        fun nextPositionRaw(randomInt: Int, position: Point): Point {
            return when (randomInt % 4) {
                0 -> Point(position.x, position.y + 1)
                1 -> Point(position.x - 1, position.y)
                2 -> Point(position.x, position.y - 1)
                else -> Point(position.x + 1, position.y)
            }
        }

    }

    fun nextPosition(aq: Aquarium, randomInt: Int): Point {
        return aq.wrap(nextPositionRaw(randomInt, position))
    }
}
