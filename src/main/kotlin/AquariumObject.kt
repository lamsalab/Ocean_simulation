interface AquariumObject {
    open val movesLeft: Int
    open val position: Pair<Int, Int>

    open fun nextPosition(randomInt: Int): Pair<Int, Int>
    open fun update(objects: Set<AquariumObject>): UpdateResult
}