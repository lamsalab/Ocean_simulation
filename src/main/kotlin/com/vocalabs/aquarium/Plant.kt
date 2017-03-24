package com.vocalabs.aquarium

import com.vocalabs.aquarium.Point
import com.vocalabs.egtest.annotation.Eg

data class Plant (override val position: Point) : AquariumObject {

    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(4, 4)"),
            given = arrayOf("java.util.Collections.emptySet()", "0", "new com.vocalabs.aquarium.Aquarium(5, 5)"),
            returns = "new com.vocalabs.aquarium.UpdateResult(java.util.Collections.emptySet(), java.util.Collections.emptySet(), new com.vocalabs.aquarium.Plant(new com.vocalabs.aquarium.Point(4, 4)), new com.vocalabs.aquarium.Plant(new com.vocalabs.aquarium.Point(4, 4)))")
    @Eg(construct = arrayOf("new com.vocalabs.aquarium.Point(5, 5)"),
            given = arrayOf("java.util.Collections.emptySet()", "3", "new com.vocalabs.aquarium.Aquarium(10, 10)"),
            returns = "new com.vocalabs.aquarium.UpdateResult(java.util.Collections.emptySet(), java.util.Collections.emptySet(), new com.vocalabs.aquarium.Plant(new com.vocalabs.aquarium.Point(5, 5)), new com.vocalabs.aquarium.Plant(new com.vocalabs.aquarium.Point(5, 5)))")
    override fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult {
        return UpdateResult(setOf(), setOf(), this, this)
    }
}