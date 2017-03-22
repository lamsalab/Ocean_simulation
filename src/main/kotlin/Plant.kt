data class Plant (override val movesLeft: Int, override val position: Point) : AquariumObject{
    override fun update(objects: Set<AquariumObject>): UpdateResult{

        return UpdateResult()
    }
}