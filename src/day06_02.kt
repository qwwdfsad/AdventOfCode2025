fun main() {
    val input = readInput("day06")
    val ops = input.last().trim().split(Regex("\\s+"))
    val rows = input.dropLast(1)
    var result = 0L
    // Index of the first whitespace after the current column of numbers
    var startIdx = 0
    for ((_, op) in ops.withIndex()) {
        var endIdx = -1
        for (row in rows) {
            val idx = row.indexOf(' ', startIdx + 1).let { if (it == -1) row.length else it }
            endIdx = maxOf(endIdx, idx)
        }
        // Operate in startIdx..endIdx withing each row
        val operandsCount = endIdx - startIdx
        val operands = (1..operandsCount).map {
            val idx = it
            buildString {
                rows.forEach {
                    val digitOrSpace = it[startIdx + idx - 1]
                    if (digitOrSpace != ' ') append(digitOrSpace)
                }
            }
        }
        result += when (op) {
            "*" -> operands.fold(1L) { acc, r -> acc * r.toLong() }
            "+" -> operands.fold(0L) { acc, r -> acc + r.toLong() }
            else -> error(op)
        }.also { println(it) }
        startIdx = endIdx + 1
    }
    println(result)
}
