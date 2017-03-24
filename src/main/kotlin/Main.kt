import com.vocalabs.aquarium.*
import com.vocalabs.egtest.annotation.Eg
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

object Main {

    val aquarium = Aquarium(10, 10)
    val random = Random()

    fun generateObjectList(numPlants: Int, numMinnows: Int, numSharks: Int): List<AquariumObject> {
        var retList: Set<AquariumObject> = setOf()

        for (i in 1..numPlants) {
            retList = retList.plus(generatePlant(retList))
        }
        for (i in 1..numMinnows) {
            retList = retList.plus(generateMinnow(retList))
        }
        for (i in 1..numSharks) {
            retList = retList.plus(generateShark(retList))
        }
        return retList.toList()
    }

    fun generatePoint(): Point {
        return aquarium.wrap(Point(random.nextInt(), random.nextInt()))
    }

    fun generatePlant(allObects: Set<AquariumObject>): Plant {
        while (true) {
            val potentialPlant = Plant(generatePoint())
            if (potentialPlant.canPlaceObject(potentialPlant.position.x, potentialPlant.position.y, allObects)) {
                return potentialPlant
            }
        }
    }

    fun generateMinnow(allObects: Set<AquariumObject>): Minnow {
        while (true) {
            val potentialMinnow = Minnow(generatePoint(), Fish.MAXIMUM_CAPACITY)
            if (potentialMinnow.canPlaceObject(potentialMinnow.position.x, potentialMinnow.position.y, allObects)) {
                return potentialMinnow
            }
        }
    }

    fun generateShark(allObects: Set<AquariumObject>): Shark {
        while (true) {
            val potentialShark = Shark(generatePoint(), Fish.MAXIMUM_CAPACITY)
            if (potentialShark.canPlaceObject(potentialShark.position.x, potentialShark.position.y, allObects)) {
                return potentialShark
            }
        }
    }

    /*@Eg(construct = arrayOf(""),
            given = arrayOf("new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5))"),
            returns = "new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 2), 4))")*/
    @JvmStatic
    fun updateObjects(listOfObjects: List<AquariumObject>): List<AquariumObject> {
        var updateResults: List<UpdateResult> = listOfObjects.map { aquariumObject ->  aquariumObject.update(listOfObjects.toSet(), random.nextInt(), aquarium)}
        var killSet: Set<AquariumObject> = Collections.emptySet()
        var addSet: Set<AquariumObject?> = Collections.emptySet()
        var returnList: MutableList<AquariumObject> = mutableListOf()

        updateResults.map { it -> it.killObjects }.forEach { it -> killSet = killSet.plus(it) }
        updateResults.map { it -> it.addObjects }.forEach { it -> addSet = addSet.plus(it) }

        for (i in 0..1) {
            val potentialPlant = Plant(aquarium.wrap(Point(random.nextInt(), random.nextInt())))
            if (potentialPlant.canPlaceObject(potentialPlant.position.x, potentialPlant.position.y, listOfObjects.toSet())) {
                addSet = addSet.plus(potentialPlant)
            }
        }

        updateResults = updateResults.filter { it -> !(killSet.contains(it.old)) }
        returnList.addAll(updateResults.map { it -> it.new })
        returnList.addAll(addSet.filterNotNull())
        return returnList
    }

    fun printOcean(listOfObjects: List<AquariumObject>){
        for (y in 0..(aquarium.height - 1)) {
            for (x in 0..(aquarium.width - 1)) {
                var charPrinted = false

                for (obj in listOfObjects) {
                    if (!obj.equals(null) && obj.position.equals(Point(x, y))) {
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
        var allObjects = generateObjectList(3, 3, 3)
        printOcean(allObjects)
        while(true){
            allObjects = updateObjects(allObjects)
            printOcean(allObjects)
            Thread.sleep(1000)
        }

    }
}