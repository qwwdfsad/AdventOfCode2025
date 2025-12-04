

fun main() {
    val input = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent().split("\n").map { it.toList() }

//        val input= readInput("day04")

    fun isRoll(y: Int, x: Int) = input.getOrNull(y)?.getOrNull(x) == '@'

    var total = 0
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
            if (rolls < 4) total++
        }
    }

    println(total)
}
