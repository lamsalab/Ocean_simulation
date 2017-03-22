data class Fish (override val movesLeft: Int, override val position: Point) : AquariumObject {

    override fun nextPosition(randomInt: Int): Point {
        return when (randomInt) {
            0 -> Point(position.x, position.y + 1)
            1 -> Point(position.x - 1, position.y)
            2 -> Point(position.x, position.y - 1)
            else -> Point(position.x + 1, position.y)
        }
    }

    override fun update(objects: Set<AquariumObject>): UpdateResult {
        return UpdateResult()
    }
}