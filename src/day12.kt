fun main() {
    val input2 = """0:
###
##.
##.

1:
###
##.
.##

2:
.##
###
##.

3:
##.
###
##.

4:
###
#..
###

5:
###
.#.
###

4x4: 0 0 0 0 2 0
12x5: 1 0 1 0 2 2
12x5: 1 0 1 0 3 2"""

    val lines = readInputText("day12").split("\n\n")
    val tests = lines.last()
    var answer = 0
    for (test in tests.lines().filter { it.isNotBlank() }) {
        val space = test.substringBefore("x").toInt() *
                test.substringAfter("x").substringBefore(":").toInt()
        val required = test.substringAfter(": ").split(" ").sumOf { it.toInt() * 7 }
        if (space >= required) {
            answer++
        }
    }
    println(answer)
}
