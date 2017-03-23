import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

object Main {

    val aquarium = Aquarium(10, 10)
    var allObjects: List<AquariumObject> = listOf(Shark(Point(1, 1), 5), Minnow(Point(2, 0), 5), Plant(Point(5, 1)))

    fun updateObjects() {

        val random = Random()
        val intStream= random.ints(0, 100).limit(allObjects.size.toLong())
        val intArray = intStream.toArray()

        var updateResults: List<UpdateResult> = allObjects.mapIndexed { i, aquariumObject ->  aquariumObject.update(allObjects.toSet(), intArray[i], aquarium)}
        var killSet: Set<AquariumObject> = Collections.emptySet()
        updateResults.map { it -> it.killObjects }.forEach { it -> killSet = killSet.plus(it) }
        updateResults = updateResults.filter { it -> !(killSet.contains(it.old)) }
        allObjects = updateResults.map { it -> it.new }
    }

    fun updateOcean(){
        for (i in 0..aquarium.height) {
            for (j in 0..aquarium.width) {
                for (obj in allObjects) {
                    if (obj.position.equals(Point(j, i))) {
                        when (obj) {
                            is Plant -> System.out.print('P')
                            is Minnow -> System.out.print('M')
                            is Shark -> System.out.print('S')
                        }
                    }
                    else System.out.print(' ')
                }
            }
            System.out.print('\n')
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {

        while(true){
            updateObjects()
            updateOcean()
            Thread.sleep(1000)
        }

    }
}