package com.vocalabs.aquarium

import com.vocalabs.aquarium.Point

//import com.vocalabs.egtest.annotation.Eg

class Aquarium(w: Int, h: Int) {
    val width = w
    val height = h

   /* @Eg(construct = arrayOf("5", "10"),
            given = arrayOf("new com.vocalabs.aquarium.Point(5, 5)"),
            returns = "new com.vocalabs.aquarium.Point(0, 5)")
    @Eg(construct = arrayOf("3", "3"),
            given = arrayOf("new com.vocalabs.aquarium.Point(1, -1)"),
            returns = "new com.vocalabs.aquarium.Point(1, 2)")*/
    fun wrap(point: Point): Point {
        return Point(fmod(point.x, width), fmod(point.y, height))
    }

    private fun fmod(x: Int, y: Int): Int {
        return ((x % y) + y) % y
    }
}