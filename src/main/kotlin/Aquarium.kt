import com.vocalabs.egtest.annotation.Eg

class Aquarium(w: Int, h: Int) {
    val width = w
    val height = h

    @Eg(construct = arrayOf("5", "10"),
            given = arrayOf("new Point(5, 5)"),
            returns = "new Point(0, 5)")
    @Eg(construct = arrayOf("3", "3"),
            given = arrayOf("new Point(2, 3)"),
            returns = "new Point(2, 0)")
    fun wrap(point: Point): Point {
        return Point(point.x % width, point.y % height)
    }
}