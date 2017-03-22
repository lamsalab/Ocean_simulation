import java.util.*
import java.util.stream.IntStream

class Main {

    val width = 10
    val height = 10

    var allObjects: List<AquariumObject> = listOf(Shark(Point(0,0),5), Minnow(Point(2,0),5),Plant(Point(5, 1)))
    var ocean: MutableList<Char> = Collections.emptyList()


    fun updateObjects() {
        var updateResults: List<UpdateResult> = allObjects.map { it -> it.update(allObjects.toSet(), IntStream.of(0)) }
        var killSet: Set<AquariumObject> = Collections.emptySet()
        updateResults.map { it -> it.killObjects }.forEach { it -> killSet = killSet.plus(it) }
        updateResults = updateResults.filter { it -> !(killSet.contains(it.old)) }
        allObjects = updateResults.map { it -> it.new }
    }

    fun updateOcean() {
        var ocean: MutableList<Char> = Collections.emptyList()

        for (i in 0..width) {
            for (j in 0..height) {
                for (obj in allObjects) {
                    if (obj.position.equals(Point(i, j))) {
                        when (obj) {
                            is Plant -> ocean[(i + (j * width))] = 'P'
                            is Minnow -> ocean[(i + (j * width))] = 'M'
                            is Shark -> ocean[(i + (j * width))] = 'S'
                        }
                    }
                    else ocean[(i + (j * width))] = ' '
                }
            }
        }
    }

    fun displayOcean() {

    }

    fun main(args: Array<String>) {


    }
}