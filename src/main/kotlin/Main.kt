import com.vocalabs.aquarium.*
import com.vocalabs.egtest.annotation.Eg
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

object Main {

    val random = Random()

    fun generateObjectList(numPlants: Int, numMinnows: Int, numSharks: Int, aquarium: Aquarium): List<AquariumObject> {
        var retList: Set<AquariumObject> = setOf()

        for (i in 1..numPlants) {
            retList = retList.plus(generatePlant(retList, aquarium))
        }
        for (i in 1..numMinnows) {
            retList = retList.plus(generateMinnow(retList, aquarium))
        }
        for (i in 1..numSharks) {
            retList = retList.plus(generateShark(retList, aquarium))
        }
        return retList.toList()
    }

    fun generatePoint(aquarium: Aquarium): Point {
        return aquarium.wrap(Point(random.nextInt(), random.nextInt()))
    }

    fun generatePlant(allObjects: Set<AquariumObject>, aquarium: Aquarium): Plant {
        while (true) {
            val potentialPlant = Plant(generatePoint(aquarium))
            if (potentialPlant.canPlaceObject(potentialPlant.position.x, potentialPlant.position.y, allObjects)) {
                return potentialPlant
            }
        }
    }

    fun generateMinnow(allObjects: Set<AquariumObject>, aquarium: Aquarium): Minnow {
        while (true) {
            val potentialMinnow = Minnow(generatePoint(aquarium), Fish.MAXIMUM_CAPACITY)
            if (potentialMinnow.canPlaceObject(potentialMinnow.position.x, potentialMinnow.position.y, allObjects)) {
                return potentialMinnow
            }
        }
    }

    fun generateShark(allObjects: Set<AquariumObject>, aquarium: Aquarium): Shark {
        while (true) {
            val potentialShark = Shark(generatePoint(aquarium), Fish.MAXIMUM_CAPACITY)
            if (potentialShark.canPlaceObject(potentialShark.position.x, potentialShark.position.y, allObjects)) {
                return potentialShark
            }
        }
    }

    /*@Eg(construct = arrayOf(""),
            given = arrayOf("new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 1), 5))"),
            returns = "new java.util.ArrayList().add(new com.vocalabs.aquarium.Minnow(new com.vocalabs.aquarium.Point(1, 2), 4))")
            This test runs when 0 is inputted into aquariumObject.update() instead of random.nextInt()*/

    @JvmStatic
    fun updateObjects(listOfObjects: List<AquariumObject>, aquarium: Aquarium): List<AquariumObject> {
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

    fun printOcean(listOfObjects: List<AquariumObject>, aquarium: Aquarium){
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
        val aquarium = Aquarium(10, 10)
        var allObjects = generateObjectList(4, 4, 4, aquarium)
        printOcean(allObjects, aquarium)
        while(true){
            allObjects = updateObjects(allObjects, aquarium)
            printOcean(allObjects, aquarium)
            Thread.sleep(1000)
        }

    }
}