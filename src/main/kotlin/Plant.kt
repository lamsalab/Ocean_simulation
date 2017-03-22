data class Plant (override val movesLeft: Int, override val position: Point) : AquariumObject{
    override fun nextPosition(randomInt: Int): Point{
        return Point(position.x, position.y)
    }

    override fun update(objects: Set<AquariumObject>): UpdateResult{
        return UpdateResult()
    }
}