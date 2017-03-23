import java.util.stream.IntStream

data class Plant (override val position: Point) : AquariumObject{

    @Eg(construct = arrayOf("new Point(4, 4)"),
            given = arrayOf("new Aquarium(5, 5), 0"),
            returns = "new Point(4, 4)")
    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult{
        return UpdateResult(setOf(), this, this)
    }
}