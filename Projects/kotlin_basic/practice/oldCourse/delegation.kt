interface IMoving {
    fun move()
}

class Walking : IMoving {
    override fun move() {
        println("Walking")
    }
}
class Plecostomus(fishColor: FishColor = GoldColor) :  FishAction by PrintingFishAction("eat algae") ,FishColor by fishColor {}

class Animal(movable : IMoving) : IMoving by movable {
 //by movable == by Walking() == above's by PrintingFishAction("eat algae")，only difference is former is a parameter.
}

fun main() {
    var walking = Walking()
    var animal = Animal(walking)
    animal.move()
}