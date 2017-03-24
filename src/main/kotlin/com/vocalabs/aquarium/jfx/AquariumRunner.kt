package com.vocalabs.aquarium.jfx

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.stage.Stage
import java.util.*


class AquariumRunner : javafx.application.Application() {

    override fun start(primaryStage: Stage) {

        primaryStage.title = "Drawing Operations Test"
        val root = Group()
        val canvas = Canvas(1000.0, 1000.0)
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

            val scanner = Scanner(System.`in`)
            print("Enter the width (Type:Double): ")
            val x = scanner.nextDouble()
            print("Enter the height (Type:Double): ")
            val y = scanner.nextDouble()

            val runnable = Runnable {
                while (mainGc == null) {
                    Thread.sleep(100)
                }
                Simulator(mainGc!!, x, y).run()
            }

            val thread = Thread(runnable)
            thread.isDaemon = true
            thread.start()

            Platform.setImplicitExit(true)
            Application.launch(AquariumRunner::class.java)
        }
    }
}