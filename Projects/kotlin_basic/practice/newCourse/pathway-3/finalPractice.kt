enum class Daypart {
    MORNING, AFTERNOON, EVENING
}
data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)
val studyEvent = Event(
    title= "Study Kotlin",
    description= "Commit to studying Kotlin at least 15 minutes per day.",
    daypart= Daypart.EVENING,
    durationInMinutes= 15
)
val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event5)

val shortEvents = events.filter { it.durationInMinutes  < 60}

//早中晚分組 -> groupBy()
val group = events.groupBy { it.daypart}
//用extension property,這樣property本體有變動 別的團隊因為沒有直接用property本體，而是用了extension，所以基本不會受任何影響，我們也只需要改那個extension property的內容而已!
val Event.durationOfEvent: String
    get() = if (durationInMinutes < 60) "short" else "long"

fun main() {
// print(studyEvent)
// print(events)
// print("You have ${shortEvents.size} short events.")
// group.forEach { (daypart,events) -> println("$daypart: ${events.size} events") }
println(events[0].durationOfEvent)

}
