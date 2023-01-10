// abstract class AquariumFish {
//     abstract val color: String
// }


fun main() {
    Plecostomus().eat()
}

interface FishAction {
    fun eat()
}

interface FishColor {
    val color: String
}


//remove AquariumFish class, instead, implement two interface.
class Shark : FishColor, FishAction {
    override val color = "grey"
    override fun eat() {
        println("Shark hunt and eat fish")
    }
}

//singleton class(==> singleton pattern)
object GoldColor: FishColor {
    //add interface delegation for FishColor
    override val color = "gold"
}


//interface delegation for FishAction
class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}

class Plecostomus(fishColor: FishColor = GoldColor) :  FishAction by PrintingFishAction("eat algae") ,FishColor by fishColor {
    init {
        println(fishColor)
    }
    //by GoldColor to the class declaration, creating the delegation.
    //What this says is that instead of implementing FishColor, use the implementation provided by GoldColor. 
    //So every time color is accessed, it is delegated to GoldColor.

    // * remove this line
    // override val color = "gold"
    // * remove
    // Because we get Plecostomus's color from GoldColor,so we can remove this.
    //And all instances of Plecostomus will be "gold"
    // But these fish actually come in many colors. 
    // You can address this by adding a constructor parameter for the color with GoldColor as the default color for Plecostomus.


    // * remove as well,coz we have implemented FishAction interface.
    // override fun eat() {
    //     println(fishColor)
    // }

}
