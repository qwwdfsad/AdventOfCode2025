fun main() {
//    var input = """
//        ..@@.@@@@.
//        @@@.@.@.@@
//        @@@@@.@.@@
//        @.@@@@..@.
//        @@.@@@@.@@
//        .@@@@@@@.@
//        .@.@.@.@@@
//        @.@@@.@@@@
//        .@@@@@@@@.
//        @.@.@@@.@.
//    """.trimIndent().split("\n").map { it.toMutableList() }

    var input = readInput("day04").map { it.toMutableList() }
    var copy = input.map { it.toMutableList() }

    fun isRoll(y: Int, x: Int) = input.getOrNull(y)?.getOrNull(x) == '@'

    var removed: Int
    var removedTotal = 0
    do {
        removed = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                var rolls = 0
                if (!isRoll(i, j)) continue
                for (k in -1..1) {
                    for (l in -1..1) {
                        if (isRoll(i + k, j + l) && !(k == 0 && l == 0)) {
                            rolls++
                        }
                    }
                }
                if (rolls < 4) {
                    copy[i][j] = '.'
                    ++removed
                }
            }
        }
        input = copy.map { it.toMutableList() }
        removedTotal += removed
    } while (removed != 0)

    println(removedTotal)
}
