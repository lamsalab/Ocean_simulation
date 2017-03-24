package com.vocalabs.aquarium

data class UpdateResult(val killObjects: Set<AquariumObject>, val old: AquariumObject, val new: AquariumObject)