import java.util.stream.IntStream

interface AquariumObject {
    open var movesLeft: Int
    open val position: Point

    open fun nextPosition(randomInt: Int): Point
    open fun update(allObjects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult
}