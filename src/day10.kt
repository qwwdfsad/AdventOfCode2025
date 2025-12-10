import kotlin.math.min

fun main() {
//    val input = """[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
//[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
//[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"""
    val input = readInput("day10")

    var answer = 0L
    for (line in input) {
        val conf = line.substringAfter("[").substringBefore("]").map {
            if (it == '.') false else true
        }.toBooleanArray()

        val buttons = line.substringAfter("]")
            .substringBefore("{").trim()
            .split(" ")
            .map { it.removeSurrounding("(", ")") }
            .map { it.split(",").map { it.toInt() } }

        // Solve
        fun solve(idx: Int, state: BooleanArray, presses: Int): Int {
            if (state.contentEquals(conf)) return presses
            if (idx == buttons.size) return Int.MAX_VALUE
            val b1 = run {
                val st = state.copyOf()
                // Apply
                buttons[idx].forEach { st[it] = !st[it] }
                solve(idx + 1, st, presses + 1)
            }
            val b2 = run {
                solve(idx + 1, state, presses)
            }
            return min(b1, b2)
        }
        val min = solve(0, BooleanArray(conf.size) { false }, 0)
        answer += min
    }
    println(answer)
}
