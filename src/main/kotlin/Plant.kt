import java.util.stream.IntStream

data class Plant (override val position: Point) : AquariumObject{

    override fun update(objects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult{
        return UpdateResult(setOf(), this, this)
    }
}