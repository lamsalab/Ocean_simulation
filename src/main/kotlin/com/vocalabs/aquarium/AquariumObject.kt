package com.vocalabs.aquarium

import com.vocalabs.aquarium.Point
import java.util.stream.IntStream

interface AquariumObject {

    val position: Point

    fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult

    fun canPlaceObject(x: Int, y: Int, existingObjects: Set<AquariumObject>): Boolean {
        var retVal = true
        for (obj in existingObjects) {
            if ((obj.position.x == x) && (obj.position.y == y)) {
                retVal = false
            }
        }
        return retVal
    }
}