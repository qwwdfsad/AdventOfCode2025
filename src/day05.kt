import java.util.LinkedList
import java.util.concurrent.CopyOnWriteArrayList


fun main() {
//    val input = """3-5
//10-14
//16-20
//12-18
//
//1
//5
//8
//11
//17
//32""".trimIndent()
    val input= readInputText("day05")

    val sep = input.indexOf("\n\n")
    val ranges = input.substring(0, sep).lines().map {
        it.split("-").map { it.toLong() }.let { (a, b) -> a..b }
    }

    // NB: very suboptimal solution
    val unprocessedRanges = CopyOnWriteArrayList<LongRange>(ranges)
    val result = HashSet<LongRange>()
    while (unprocessedRanges.isNotEmpty()) {
        val origin = unprocessedRanges.removeFirst()
        var range = origin
        for (candidate in unprocessedRanges) {
            // Non-overlapping: [] () or () []
            if (candidate.first > range.last || candidate.last < range.first) continue
            range = minOf(range.first, candidate.first)..maxOf(range.last, candidate.last)
            unprocessedRanges.remove(candidate)
        }
        if (range != origin) unprocessedRanges.addFirst(range)
        else result.add(range)
    }

    println(result.sumOf { it.last - it.first + 1 })
}
