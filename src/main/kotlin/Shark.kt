import java.util.stream.IntStream

data class Shark (override val position: Point, override val movesLeft: Int) : Fish{

    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult {
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
                    Shark(nextPosition(aquarium, randomInt), movesLeft - 1)
                else
                    Shark(nextPosition(aquarium, randomInt), Fish.MAXIMUM_CAPACITY))
    }
}