import java.util.stream.IntStream

data class Minnow(override val position: Point, override val movesLeft: Int) : Fish {

    override fun update(allObjects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult {
        var killSet: Set<AquariumObject> = setOf()

        for (obj in allObjects) {
            if (obj.position == this.position) {
                if (obj is Plant) {
                    killSet = killSet.plus(obj)
                }
            }
        }

        return UpdateResult(killSet, this,
                if (killSet.isEmpty())
                    Minnow(nextPosition(randomIntStream.findFirst().asInt), movesLeft - 1)
                else
                    Minnow(nextPosition(randomIntStream.findFirst().asInt), Fish.MAXIMUM_CAPACITY))
    }


}