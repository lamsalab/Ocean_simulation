import java.util.stream.IntStream

data class Minnow(override val position: Point, override val movesLeft: Int) : Fish {



    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult {
        var killSet: Set<AquariumObject> = setOf()

        for (obj in allObjects) {
            if (obj.position.equals(this.position)) {
                if (obj is Plant) {
                    killSet = killSet.plus(obj)
                }
            }
        }

        return UpdateResult(killSet, this,
                if (killSet.isEmpty())
                    Minnow(nextPosition(aquarium, randomInt), movesLeft - 1)
                else
                    Minnow(nextPosition(aquarium, randomInt), Fish.MAXIMUM_CAPACITY))
    }

    @Eg(construct = arrayOf("new Point(1, 1), 5"),
            given = arrayOf("new Aquarium(5, 5), 1"),
            returns = "new Point(0, 1)")
    @Eg(construct = arrayOf("new Point(4, 4), 5"),
            given = arrayOf("new Aquarium(5, 5), 0"),
            returns = "new Point(4, 0)")
    override fun nextPosition(aq: Aquarium, randomInt: Int): Point {
        return super.nextPosition(aq, randomInt)
    }


}