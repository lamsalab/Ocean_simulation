package com.vocalabs.aquarium
import com.vocalabs.egtest.annotation.Eg
import com.vocalabs.aquarium.Point
import java.util.stream.IntStream

data class Minnow(override val position: Point, override val movesLeft: Int) : Fish {

    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(1, 1)", "5"),
            given = arrayOf("java.util.Collections.emptySet()", "0", "new com.vocalabs.aquarium.Aquarium(5, 5)"),
            returns = "new com.vocalabs.aquarium.UpdateResult(java.util.Collections.emptySet(), java.util.Collections.emptySet(), new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5), new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 2), 4))")
    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(1, 0)", "5"),
            given = arrayOf("java.util.Collections.emptySet()", "2", "new com.vocalabs.aquarium.Aquarium(3, 3)"),
            returns = "new com.vocalabs.aquarium.UpdateResult(java.util.Collections.emptySet(), java.util.Collections.emptySet(), new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 0), 5), new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 2), 4))")
    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult {
        var killSet: Set<AquariumObject> = setOf()
        var addSet: Set<AquariumObject?> = setOf()

        for (obj in allObjects) {
            if (obj.position == this.position) {
                if (obj is Plant) {
                    killSet = killSet.plus(obj)
                    addSet = addSet.plus(newFish(allObjects.plus(addSet.filterNotNull())))
                }
            }
        }

        if (killSet.isEmpty() && ((movesLeft - 1) == 0)) {
            killSet = killSet.plus(this)
        }

        if ((killSet.isEmpty()) || (killSet.elementAt(0) !is Plant))
            return UpdateResult(killSet, addSet, this, Minnow(nextPosition(aquarium, randomInt), movesLeft - 1))
        else
            return UpdateResult(killSet, addSet, this, Minnow(nextPosition(aquarium, randomInt), Fish.Companion.MAXIMUM_CAPACITY))
    }

    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(1, 1)", "5"),
            given = arrayOf("new com.vocalabs.aquarium.Aquarium(5, 5)", "1"),
            returns = "new com.vocalabs.aquarium.Point(0, 1)")
    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(4, 4)", "5"),
            given = arrayOf("new com.vocalabs.aquarium.Aquarium(5, 5)", "0"),
            returns = "new com.vocalabs.aquarium.Point(4, 0)")
    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(1, 0)", "5"),
            given = arrayOf("new com.vocalabs.aquarium.Aquarium(3, 3)", "2"),
            returns = "new com.vocalabs.aquarium.Point(1, 2)")
    override fun nextPosition(aq: Aquarium, randomInt: Int): Point {
        return super.nextPosition(aq, randomInt)
    }

    override fun newFish(existingObjects: Set<AquariumObject>): Fish? {
        for (i in 1..5) {
            var x = this.position.x - i
            var y = this.position.y - i
            for (j in 0 .. 2) {
                if (this.canPlaceObject(x, y, existingObjects))
                    return Minnow(Point(x, y), Fish.MAXIMUM_CAPACITY)
                x += i * j
                if (this.canPlaceObject(x, y, existingObjects))
                    return Minnow(Point(x, y), Fish.MAXIMUM_CAPACITY)
                y += i * j
                if (this.canPlaceObject(x, y, existingObjects))
                    return Minnow(Point(x, y), Fish.MAXIMUM_CAPACITY)
                x -= i * j
                if (this.canPlaceObject(x, y, existingObjects))
                    return Minnow(Point(x, y), Fish.MAXIMUM_CAPACITY)
                y -= i * j
            }
        }
        return null
    }
}