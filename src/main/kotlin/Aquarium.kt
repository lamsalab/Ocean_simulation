class Aquarium(w: Int, h: Int) {
    val width = w
    val height = h

    fun wrap(point: Point): Point {
        return Point(point.x.mod(width),point.y.mod(height))
    }
}