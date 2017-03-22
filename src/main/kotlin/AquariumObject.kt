
interface AquariumObject {
    open val movesLeft: Int
    open val position: Pair<Int, Int>

    open fun nextPosition()
}