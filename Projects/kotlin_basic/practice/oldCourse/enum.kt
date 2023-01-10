fun main() {
    println(Color.GREEN.name)
    println(Color.GREEN.rgb)
    println(Color.GREEN.ordinal)
    println(Color.RED.ordinal)
}
enum class Color(val rgb: Int) {
   RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}