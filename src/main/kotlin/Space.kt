import java.util.stream.IntStream

interface Space {
    fun move (currentPosition: Int, vector: Int): Point
    fun fill(density: Int, random: IntStream): Collection<Point>
}