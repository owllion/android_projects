fun main() {
    run()
}
fun run () {
    val list = mutableListOf("one","two","three")
    println(list.add("971"))
    //IF you want to change the list, 
    //must use mutableListOf(), can not use listOf, this kind of list is immutable meaning you cannot use any method to modify it.

    println(list.sumBy { it.length })
    for (s in list.listIterator()) {
        println("$s ")
    }
    val dic = hashMapOf(1 to 80,"two" to 2)
    println(dic.get(1))
    println(dic.getOrDefault("5564",1))
    //If there is only type existing in the map,then your default value must be that type or you'll get the error -> inferred type is String but Int was expected

    println(dic.getOrElse("TestNothing", {println("Nothing just print")}))
}