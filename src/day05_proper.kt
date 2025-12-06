import java.util.concurrent.CopyOnWriteArrayList


fun main() {
//    val input = """3-5
//10-14
// 12-18
//16-20
//
//1
//5
//8
//11
//17
//32""".trimIndent()
    val input = readInputText("day05")


    val sep = input.indexOf("\n\n")
    // (startIdx, true), (endIdx, false)
    val ranges = input.substring(0, sep).lines().map {
        it.split("-").map { it.toLong() }.let { (a, b) -> a..b }
    }.flatMap { listOf(it.first to true, it.last to false) }.toMutableList()


    ranges.sortWith(Comparator<Pair<Long, Boolean>> { a, b -> a.first.compareTo(b.first) }
        .then { a, b -> b.second.compareTo(a.second) })

    // I.e. 1-3, 4-5 is
    var answer = 0L
    var overlaps = 0
    var startPos = -1L
    for ((value, isEnd) in ranges) {
        if (startPos == -1L) {
            startPos = value
        }

        if (isEnd) {
            --overlaps
        } else {
            ++overlaps
        }

        if (overlaps == 0) {
            answer += value - startPos + 1L
            startPos = -1L
        }
    }
    println(answer)
}
