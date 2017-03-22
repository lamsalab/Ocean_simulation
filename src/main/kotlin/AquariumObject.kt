interface AquariumObject {
    open val movesLeft: Int
    open val position: Point

    open fun nextPosition(randomInt: Int): Point
    open fun update(objects: Set<AquariumObject>): UpdateResult
}