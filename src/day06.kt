fun main() {
//    val input = """123 328  51 64
// 45 64  387 23
//  6 98  215 314
//*   +   *   +""".trimIndent().lines()

    val input = readInput("day06")
    val ops = input.last().split(Regex("\\s+"))
    val rows = input.dropLast(1).map { it.trim().split(Regex("\\s+")).map { it.toLong() } }
    var result = 0L
    for ((idx, op) in ops.withIndex()) {
        result += when (op) {
            "*" -> rows.fold(1L) { acc, r -> acc * r[idx] }
            "+" -> rows.fold(0L) { acc, r -> acc + r[idx] }
            else -> error(op)
        }
    }
    println(result)
}
