package com.vocalabs.aquarium.jfx

import com.vocalabs.aquarium.*
import javafx.application.Platform
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

/**
 * Runs the simulator
 */
class Simulator(val gc: GraphicsContext, var x: Double, var y: Double) : Runnable {

    override fun run() {

        val aquarium = Aquarium(x.toInt() / 50, y.toInt() / 50)

        val allObjects: List<AquariumObject> = Main.generateObjectList(10, 10, 5, aquarium)

        var ocean: List<AquariumObject> = allObjects
        drawOcean(gc, ocean, aquarium)
        while(true) {
            Thread.sleep(750)
            Platform.runLater {
                ocean = Main.updateObjects(ocean, aquarium)
                drawOcean(gc, ocean, aquarium)
            }
        }
    }

    private fun drawOcean(gc: GraphicsContext, ocean: List<AquariumObject>, aquarium: Aquarium) {

        gc.setFill(Color.STEELBLUE)
        gc.fillRect(0.0, 0.0, this.x, this.y)
        for (y in 0..(aquarium.height - 1)) {
            for (x in 0..(aquarium.width - 1)) {
                for (obj in ocean) {
                    if (obj.position.equals(Point(x, y))) {
                        when (obj) {
                            is Plant -> {
                                gc.setFill(Color.SEAGREEN)
                                gc.fillOval(obj.position.x * 50.0, obj.position.y * 50.0, 50.0, 50.0)
                            }
                            is Minnow -> {
                                gc.setFill(Color.TAN)
                                gc.fillOval(obj.position.x * 50.0, obj.position.y * 50.0, 50.0, 50.0)

                            }
                            is Shark -> {
                                gc.setFill(Color.SLATEGRAY)
                                gc.fillOval(obj.position.x * 50.0, obj.position.y * 50.0, 50.0, 50.0)
                            }
                        }
                    }
                }
            }
        }
    }
}