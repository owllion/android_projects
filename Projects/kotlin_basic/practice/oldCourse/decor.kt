fun main() {
    makeDecorations2()
    println(Choice.name)
    println(Choice().test)
    Choice.showDescription("Larissa")
}
fun makeDecorations2() {
    val (rocks, wood, diver) = Decoration2("crystal", "woodInput", "diverInput")
    println("$rocks $wood $diver")
}
fun makeDecorations() {
    val decoration1 = Decoration("granite")
    println(decoration1)
     val decoration2 = Decoration("slate")
    println(decoration2)
     val decoration3 = Decoration("slate")
    println(decoration3)
    println (decoration1.equals(decoration2))
    println (decoration3 == decoration2)
}
data class Decoration(val rocks: String = "granite", ) {
// val wood: String, val metal: Int
}
data class Decoration2(val rocks: String, val wood: String, val diver: String){
}


class Choice(val test: String = "Moticia") {
    companion object  {
        var name: String = "lyric"
        fun showDescription(name:String) = println("My favorite $name")
    }
}