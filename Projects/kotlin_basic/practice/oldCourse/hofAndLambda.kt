//::
// hof、lambda+type
fun main(args: Array<String>) {
    run()
}
fun run() {
    println(encodeMsg("TestEncode-1", encodeFn1))
    println(encodeMsg("TestEncode-2", ::encodeFn2))
    println(encodeMsg("ENCODE-Upper-3") {input -> input.lowercase()})
    val list = listOf(1,2,3,10,15,80)
    val res = list.filter {it > 10}
    println(res)
    // println(val res = list.filter {it > 10})
}
fun encodeMsg(msg: String, encode: (String) -> String): String{
    return encode(msg)  
} 

val encodeFn1 : (String) -> String = { input -> input.reversed() }
fun encodeFn2(input: String): String = input.uppercase()


