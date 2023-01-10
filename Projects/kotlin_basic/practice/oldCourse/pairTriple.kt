fun main() {
    run()
}
fun run () {
    val pairs = ("this is pairs one" to "pair one - two") to "real two"
    val (first, second) = pairs

    val triple = Triple("pair in triple" to "pair2",8,"Test")
    val(one,two,three)= triple
    // println(pairs.first)
    // println(first.second)
    // println(second)
    println((triple.third).toList())
    println("Thsi is $one, $two, $three")

}
