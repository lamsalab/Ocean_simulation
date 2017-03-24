package com.vocalabs.aquarium

import com.vocalabs.aquarium.Point
import java.util.stream.IntStream

interface AquariumObject {

    val position: Point

    fun update(allObjects: Set<AquariumObject>, randomInt: Int, aquarium: Aquarium): UpdateResult
}