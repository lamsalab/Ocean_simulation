package com.vocalabs.aquarium

//import com.vocalabs.egtest.annotation.Eg
import com.vocalabs.aquarium.Point
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

object Main {

    val aquarium = Aquarium(10, 10)
    val allObjects: List<AquariumObject> = listOf(Shark(Point(1, 1), 5), Minnow(Point(2, 0), 5), Plant(Point(5, 1)))

   /* @Eg(construct = arrayOf(""),
            given = arrayOf("new ArrayList().addAll(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5))"),
            returns = "new ArrayList().addAll(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 4))")*/
    fun updateObjects(listOfObjects: List<AquariumObject>): List<AquariumObject> {
        val random = Random()
        //val intStream= random.ints(0, 100).limit(listOfObjects.size.toLong())
        val intArray = intStream.toArray()

        var updateResults: List<UpdateResult> = listOfObjects.mapIndexed { i, aquariumObject ->  aquariumObject.update(listOfObjects.toSet(), intArray[i], aquarium)}
        var killSet: Set<AquariumObject> = Collections.emptySet()
        updateResults.map { it -> it.killObjects }.forEach { it -> killSet = killSet.plus(it) }
        updateResults = updateResults.filter { it -> !(killSet.contains(it.old)) }
        return updateResults.map { it -> it.new }
    }

    fun printOcean(listOfObjects: List<AquariumObject>){
        for (y in 0..aquarium.height) {
            for (x in 0..aquarium.width) {
                var charPrinted = false

                for (obj in listOfObjects) {
                    if (obj.position.equals(Point(x, y))) {
                        when (obj) {
                            is Plant -> {
                                print('P')
                                charPrinted = true
                            }
                            is Minnow -> {
                                print('M')
                                charPrinted = true
                            }
                            is Shark -> {
                                print('S')
                                charPrinted = true
                            }
                        }
                    }
                }
                if (!charPrinted) {
                    print('-')
                }
            }
            print('\n')
        }
        print('\n')
    }

    @JvmStatic
    fun main(args: Array<String>) {
        printOcean(allObjects)
        while(true){
            printOcean(updateObjects(allObjects))
            Thread.sleep(1000)
        }

    }
}