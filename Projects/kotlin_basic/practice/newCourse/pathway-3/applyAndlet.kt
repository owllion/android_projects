class Quiz {
    //let可以讓我們不用重複寫property名稱
    //以下question1/2/3重複的部分都可以用it代替
    // fun printQuiz() {
    //     println(question1.questionText)
    //     println(question1.answer)
    //     println(question1.difficulty)
    //     println()
    //     println(question2.questionText)
    //     println(question2.answer)
    //     println(question2.difficulty)
    //     println()
    //     println(question3.questionText)
    //     println(question3.answer)
    //     println(question3.difficulty)
    //     println()
    // }
    //改成這樣
    fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }
    
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

	companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
	}
}
enum class Difficulty {
    EASY, MEDIUM, HARD
}
val Quiz.StudentProgress.progressText:String
	get() = "${answered} of ${total} answered"

fun Quiz.StudentProgress.printProgressBar() {
    repeat(Quiz.answered) {print("▓")}
   	repeat(Quiz.total - Quiz.answered){print("▒")}
    println()
    println(Quiz.progressText)
}
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)



fun main() {
     Quiz.printProgressBar()
     Quiz().apply {printQuiz()}
}