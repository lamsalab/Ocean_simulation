import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

object Main {

    val aquarium = Aquarium(10, 10)
    var allObjects: List<AquariumObject> = listOf(Shark(Point(0,0),5), Minnow(Point(2,0),5),Plant(Point(5, 1)))


    fun updateObjects() {
        var updateResults: List<UpdateResult> = allObjects.map { it -> it.update(allObjects.toSet(), IntStream.of(0), aquarium) }
        var killSet: Set<AquariumObject> = Collections.emptySet()
        updateResults.map { it -> it.killObjects }.forEach { it -> killSet = killSet.plus(it) }
        updateResults = updateResults.filter { it -> !(killSet.contains(it.old)) }
        allObjects = updateResults.map { it -> it.new }
    }

    fun updateOcean(){
        for (i in 0..aquarium.height) {
            for (j in 0..aquarium.width) {
                for (obj in allObjects) {
                    if (obj.position.equals(Point(i, j))) {
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
            //displayOcean(updateOcean())
            Thread.sleep(1000)
        }

    }
}