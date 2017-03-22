import java.util.stream.IntStream

interface AquariumObject {
    val movesLeft: Int
    val position: Point

    fun nextPosition(randomInt: Int): Point
    fun update(allObjects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult
}