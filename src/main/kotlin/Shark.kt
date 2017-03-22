import java.util.stream.IntStream

data class Shark (override val position: Point, override val movesLeft: Int) : Fish{

    override fun update(allObjects: Set<AquariumObject>, randomIntStream: IntStream): UpdateResult {
        var killSet: Set<AquariumObject> = setOf()

        for (obj in allObjects) {
            if (obj.position.equals(this.position)) {
                if (obj is Fish) {
                    killSet = killSet.plus(obj)
                }
            }
        }
        return UpdateResult(killSet, this,
                if (killSet.isEmpty())
                    Shark(nextPosition(randomIntStream.findFirst().asInt), movesLeft - 1)
                else
                    Shark(nextPosition(randomIntStream.findFirst().asInt), Fish.MAXIMUM_CAPACITY))
    }



}