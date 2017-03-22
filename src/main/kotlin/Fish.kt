data class Fish (override val movesLeft: Int, override val position: Pair<Int, Int>) : AquariumObject {

    override fun nextPosition(randomInt: Int): Pair<Int, Int> {
        return when (randomInt) {
            0 -> Pair(position.first, position.second + 1)
            1 -> Pair(position.first - 1, position.second)
            2 -> Pair(position.first, position.second - 1)
            else -> Pair(position.first + 1, position.second)
        }
    }

    override fun update(objects: Set<AquariumObject>): UpdateResult {

    }
}