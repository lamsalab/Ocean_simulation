package com.vocalabs.aquarium

import com.vocalabs.aquarium.Point
import com.vocalabs.egtest.annotation.Eg
import java.util.stream.IntStream

data class Shark (override val position: Point, override val movesLeft: Int) : Fish {

    /*@Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(1, 1)", "5"),
            given = arrayOf("java.util.Collections.emptySet()", "0", "new com.vocalabs.aquarium.Aquarium(5, 5)"),
            returns = "new com.vocalabs.aquarium.UpdateResult(java.util.Collections.emptySet(), new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 1), 5), new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 2), 4))")
    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(1, 0)", "5"),
            given = arrayOf("java.util.Collections.emptySet()", "2", "new com.vocalabs.aquarium.Aquarium(3, 3)"),
            returns = "new com.vocalabs.aquarium.UpdateResult(java.util.Collections.emptySet(), new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 0), 5), new com.vocalabs.aquarium.Shark(new com.vocalabs.aquarium.Point(1, 2), 4))")*/
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
                    Shark(nextPosition(aquarium, randomInt), Fish.Companion.MAXIMUM_CAPACITY))
    }
}