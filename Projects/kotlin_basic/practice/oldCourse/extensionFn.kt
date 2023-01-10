import java.util.Arrays 
class AnimalCate(val color: String, private val type: String)

fun main() {
    run()
}
// fun AnimalCate.isCat(): Boolean = type == "cat"
//error,coz type is private
fun AnimalCate.isDark(): Boolean = color == "dark"

// extension property,can be accessed just like a regulat property; when accessed, the getter for isRed is called to get the value. (every accessed the value ,gettter will be triggered.)
val AnimalCate.isRed: Boolean 
    get() = color == "red"

fun run() {
    val animalRes = AnimalCate(color="red",type="cat")
    println( animalRes.isRed)
    // for (i in 1..30 step 5) println(i)
    val result = 51
    when(result) {
        in 1..50 -> println("Get result.")
        else -> println("Did not get result")
    }
    repeat(2) {
        println("Test repeat")
    }
    val numbers = arrayOf(1,2,5,"TestArr")
    // println(Arrays.toString(numbers))
    // println(numbers)
    val temp = 80
    val isHot = if (temp > 20) true else false
    print(isHot)
}