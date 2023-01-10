class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

fun main() {
    //forEach()
    // cookies.forEach { 
    //     println("Menu item: ${it.price}")
    // }
    //-------------------------
    //map()
    // val fullMenu = cookies.map {
    //     "${it.name} - $${it.price}"
    // }
    // fullMenu.forEach {
    //     println("$it")
    // }
    //------------------------
    //filter()
    // val filterCookies = cookies.filter { it.softBaked}
    // filterCookies.forEach { println("${it.name}")}
    //------------------------
    //groupBy()
    // val groupedMenu = cookies.groupBy { it.softBaked }
    // print(groupedMenu)
    // print(groupedMenu[true])
    //-------------------------
    //fold() -> 就是reduce
//    val totalPrice  = cookies.fold(0.0) { total,cookie -> total + cookie.price}
//    println(totalPrice)
    //------------------------
    //sortedBy()
    val sortedRes = cookies.sortedBy { it.price}
    sortedRes.forEach { println("${it.price}")}

}