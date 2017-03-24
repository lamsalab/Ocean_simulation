import com.vocalabs.aquarium.*
import com.vocalabs.egtest.annotation.Eg
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

object Main {

    val aquarium = Aquarium(10, 10)
    var allObjects: List<AquariumObject> = listOf(Shark(Point(1, 1), 5), Minnow(Point(2, 0), 5), Plant(Point(5, 1)),
            Shark(Point(3, 3), 5), Minnow(Point (1, 6), 5), Minnow(Point(6, 3), 5), Plant(Point(8, 2)))

    /*@Eg(construct = arrayOf(""),
            given = arrayOf("new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5))"),
            returns = "new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 2), 4))")*/
    @JvmStatic
    fun updateObjects(listOfObjects: List<AquariumObject>): List<AquariumObject> {
        val random = Random()
        var updateResults: List<UpdateResult> = listOfObjects.map { aquariumObject ->  aquariumObject.update(listOfObjects.toSet(), random.nextInt(), aquarium)}
        var killSet: Set<AquariumObject> = Collections.emptySet()
        var addSet: Set<AquariumObject> = Collections.emptySet()
        var returnList: MutableList<AquariumObject> = mutableListOf()

        updateResults.map { it -> it.killObjects }.forEach { it -> killSet = killSet.plus(it) }
        updateResults.map { it -> it.addObjects }.forEach { it -> addSet = addSet.plus(it) }
        for (i in 0..1) {
            addSet = addSet.plus(Plant(aquarium.wrap(Point(random.nextInt(), random.nextInt()))))
        }

        updateResults = updateResults.filter { it -> !(killSet.contains(it.old)) }
        returnList.addAll(updateResults.map { it -> it.new })
        returnList.addAll(addSet)
        return returnList
    }

    fun printOcean(listOfObjects: List<AquariumObject>){
        for (y in 0..(aquarium.height - 1)) {
            for (x in 0..(aquarium.width - 1)) {
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
            allObjects = updateObjects(allObjects)
            printOcean(allObjects)
            Thread.sleep(1000)
        }

    }
}