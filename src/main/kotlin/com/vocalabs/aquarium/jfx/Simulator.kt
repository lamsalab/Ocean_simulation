package com.vocalabs.aquarium.jfx

import com.vocalabs.aquarium.*
import javafx.application.Platform
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color




/**
 * Runs the simulator
 */
class Simulator(val gc: GraphicsContext, val x: Double, val y: Double) : Runnable {

    override fun run() {

        val aquarium = Aquarium(x.toInt(), y.toInt())
        val allObjects: List<AquariumObject> = listOf(Shark(Point(100, 100), 10), Minnow(Point(200, 250), 10), Plant(Point(300, 100)))


        var ocean: List<AquariumObject> = allObjects
        drawOcean(gc, ocean, aquarium)
        while(true) {
            Thread.sleep(1000)
            Platform.runLater {
                ocean = Main.updateObjects(ocean)
                drawOcean(gc, ocean, aquarium)
            }
        }
    }


    private fun drawOcean(gc: GraphicsContext, ocean: List<AquariumObject>, aquarium: Aquarium) {

        gc.setFill(Color.BLUE)
        gc.fillRect(0.0, 0.0, aquarium.height.toDouble(), aquarium.width.toDouble())
        for (y in 0..this.y.toInt()) {
            for (x in 0..this.x.toInt()) {
                for (obj in ocean) {
                    if (obj.position.equals(Point(x, y))) {
                        when (obj) {
                            is Plant -> {
                                gc.setFill(Color.GREEN)
                                gc.fillOval(x.toDouble() * (this.x/10), y.toDouble() * (this.y/10), 20.0, 20.0)
                            }
                            is Minnow -> {
                                gc.setFill(Color.YELLOW)
                                gc.fillOval(x.toDouble() * (this.x/10),
                                        y.toDouble() * (this.y/10), 10.0, 40.0)

                            }
                            is Shark -> {
                                gc.setFill(Color.ORANGE)
                                gc.fillRoundRect(x.toDouble() * (this.x/10),
                                        y.toDouble() * (this.y/10), 20.0, 40.0, 2.0, 3.0)
                            }
                        }
                    }
                }
            }
        }
    }
}