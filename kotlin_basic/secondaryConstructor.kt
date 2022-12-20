fun main() {                        
    val res = Test(fuck="去你的",numberOfFish=9000)
    res.printSize()
}

class Test(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
    init {
        println("Volume: ${length*width*height /1000}")
    }
    constructor(numberOfFish: Int) : this() {
    val tank = numberOfFish * 2000 *1.1
    height = (tank/(length*width)).toInt()
  }
    constructor(fuck: String) : this(length=500) {
        println(fuck)
        // height = fuck

    }
    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm "
        )
       
    }
   init {
        println("aquarium initializing")
    }
}