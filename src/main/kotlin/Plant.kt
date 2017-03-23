import com.vocalabs.egtest.annotation.Eg

data class Plant (override val position: Point) : AquariumObject{

    @Eg(construct = arrayOf("new Point(4, 4)"),
            given = arrayOf("java.util.Collections.emptySet()", "0", "new Aquarium(5, 5)"),
            returns = "new UpdateResult(java.util.Collections.emptySet(), new Plant(new Point(4, 4)), new Plant(new Point(4, 4)))")
    @Eg(construct = arrayOf("new Point(5, 5)"),
            given = arrayOf("java.util.Collections.emptySet()", "3", "new Aquarium(10, 10)"),
            returns = "new UpdateResult(java.util.Collections.emptySet(), new Plant(new Point(5, 5)), new Plant(new Point(5, 5)))")
    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult{
        return UpdateResult(setOf(), this, this)
    }
}