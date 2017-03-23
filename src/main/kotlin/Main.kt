import com.vocalabs.egtest.annotation.Eg
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
        for (y in 0..aquarium.height) {
            for (x in 0..aquarium.width) {
                var charPrinted = false

                for (obj in allObjects) {
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

        while(true){
            updateOcean()
            updateObjects()
            Thread.sleep(1000)
        }

    }
}