package com.vocalabs.aquarium.jfx

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.stage.Stage


class AquariumRunner : javafx.application.Application() {


    override fun start(primaryStage: Stage) {
        primaryStage.title = "Drawing Operations Test"
        val root = Group()
        val canvas = Canvas(500.0, 500.0)
        val gc = canvas.graphicsContext2D
        root.children.add(canvas)
        primaryStage.scene = Scene(root)
        primaryStage.show()

        mainGc = gc
    }


    companion object {
        @Volatile
        var mainGc: GraphicsContext? = null


        @JvmStatic fun main(args: Array<String>) {

            val runnable = Runnable {
                while(true) {
                    val gc = mainGc
                    if (gc == null) {
                        Thread.sleep(1000)
                    }
                    else {
                        Simulator(gc).run()
                    }
                }
            }

            val thread = Thread(runnable)
            thread.isDaemon = true
            thread.start()

            Application.launch(AquariumRunner::class.java)
        }
    }
}