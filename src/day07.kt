data class P(val y: Int, val x: Int)

fun main() {
//    val input = """.......S.......
//...............
//.......^.......
//...............
//......^.^......
//...............
//.....^.^.^.....
//...............
//....^.^...^....
//...............
//...^.^...^.^...
//...............
//..^...^.....^..
//...............
//.^.^.^.^.^...^.
//...............""".split("\n").map { it.toCharArray() }

     val input = readInput("day07")

    var answer = 0
    var (y, x) = 0 to input[0].indexOf('S');
    val toVisit = ArrayDeque<P>()
    toVisit.addLast(P(y, x))
    fun tryAdd(y: Int, x: Int) {
        input.getOrNull(y)?.getOrNull(x)?.let { c ->
            toVisit.addLast(P(y, x))
        }
    }
    val splits = HashSet<P>()
    while (toVisit.isNotEmpty()) {
        val (y, x) = toVisit.removeFirst()
        if (input[y][x] == '^') {
            if (splits.contains(P(y, x))) continue
            answer++
            tryAdd(y + 1, x + 1)
            tryAdd(y + 1, x - 1)
            splits.add(P(y, x))
        } else {
            tryAdd(y + 1, x)
        }
    }

    println(answer)
}
