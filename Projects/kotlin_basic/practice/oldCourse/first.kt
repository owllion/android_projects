import java.lang.Math.PI
fun main() {                       
    val res = Test(length = 25, width = 25, height = 40)
    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()
   
}

open class Test(var length: Int = 100, open var width: Int = 20, open var height: Int = 40) {
    open val shape = "rectangle"
    open var water: Double = 0.0
        get() = volume * .9
    open var volume: Int 
        get() = length*width*height /1000
        set(value) {
            //value -> convention,you can set other name if you prefer.
            //it's method
            height = value * 50

        }
    // init {
    //     println("Volume: $volume")
    // }
    constructor(numberOfFish: Int) : this() {
    val tank = numberOfFish * 2000 *1.1
    height = (tank/(length*width)).toInt()
  }
    private fun printSize() {
        println(shape)
        println("Volume: $volume liters Water: $water liters (${water / volume * 100.0}% full)")
       
    }
   init {
        println("aquarium initializing")
    }
}

class TowerTank (override var height: Int, var diameter: Int): Test(height = height, width = diameter, length = diameter) {
    override var water = volume * .8
    override val shape = "cylinder"
    override var volume: Int
    get() = (width/2 * length/2 * height / 1000 * PI).toInt()
    set(value) {
        height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
    }
}
