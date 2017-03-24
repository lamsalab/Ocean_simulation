package com.vocalabs.aquarium.jfx

import com.vocalabs.aquarium.*
import javafx.application.Platform
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color




/**
 * Runs the simulator
 */
class Simulator(val gc: GraphicsContext) : Runnable {


    override fun run() {
        val aquarium = com.vocalabs.aquarium.Aquarium(10, 10)
        val allObjects: List<AquariumObject> = listOf(Shark(Point(1, 1), 5), Minnow(Point(2, 0), 5), Plant(Point(5, 1)))



        for (i in 1..10) {
            for (color in arrayOf(Color.BLUEVIOLET, Color.RED, Color.PINK)) {

                Thread.sleep(1000)

                Platform.runLater {
                    drawShapes(color, gc)
                }
            }
        }
    }


    private fun drawShapes(color: Color, gc: GraphicsContext) {

        gc.setFill(Color.BLUE)
        gc.fillRect(0.0, 0.0, 500.0, 500.0)
        gc.setFill(Color.YELLOW)
        gc.fillOval(sx, 60.0, 10.0, 40.0)
        gc.setFill(Color.ORANGE)
        gc.fillRoundRect(mx, 45.0, 20.0, 40.0, 2.0, 3.0)
        gc.setFill(Color.GREEN)
        gc.fillOval(px, 50.0, 10.0, 10.0)


        /*  gc.fill = color
        gc.stroke = Color.BLUE
        gc.lineWidth = 5.0
        gc.strokeLine(40.0, 10.0, 10.0, 40.0)
        gc.fillOval(10.0, 60.0, 30.0, 30.0)
        gc.strokeOval(60.0, 60.0, 30.0, 30.0)
        gc.fillRoundRect(110.0, 60.0, 30.0, 30.0, 10.0, 10.0)
        gc.strokeRoundRect(160.0, 60.0, 30.0, 30.0, 10.0, 10.0)
        gc.fillArc(10.0, 110.0, 30.0, 30.0, 45.0, 240.0, ArcType.OPEN)
        gc.fillArc(60.0, 110.0, 30.0, 30.0, 45.0, 240.0, ArcType.CHORD)
        gc.fillArc(110.0, 110.0, 30.0, 30.0, 45.0, 240.0, ArcType.ROUND)
        gc.strokeArc(10.0, 160.0, 30.0, 30.0, 45.0, 240.0, ArcType.OPEN)
        gc.strokeArc(60.0, 160.0, 30.0, 30.0, 45.0, 240.0, ArcType.CHORD)
        gc.strokeArc(110.0, 160.0, 30.0, 30.0, 45.0, 240.0, ArcType.ROUND)
        gc.fillPolygon(doubleArrayOf(10.0, 40.0, 10.0, 40.0),
                doubleArrayOf(210.0, 210.0, 240.0, 240.0), 4)
        gc.strokePolygon(doubleArrayOf(60.0, 90.0, 60.0, 90.0),
                doubleArrayOf(210.0, 210.0, 240.0, 240.0), 4)
        gc.strokePolyline(doubleArrayOf(110.0, 140.0, 110.0, 140.0),
                doubleArrayOf(210.0, 210.0, 240.0, 240.0), 4)*/
    }

}