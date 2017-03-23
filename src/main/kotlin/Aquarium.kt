import com.vocalabs.egtest.annotation.Eg

class Aquarium(w: Int, h: Int) {
    val width = w
    val height = h

    @Eg(construct = arrayOf("5", "10"),
        given = arrayOf("new Point(5, 5)"),
        returns = "new Point(0, 5)")
    fun wrap(point: Point): Point {
        return Point(point.x.mod(width),point.y.mod(height))
    }
}