import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.math.abs

fun readInput(name: String) =
    Path("/Users/qwwdfsad/workspace/aoc25/inputs/$name.txt").readLines().filter { it.isNotBlank() }

fun readInputText(name: String) =
    Path("/Users/qwwdfsad/workspace/aoc25/inputs/$name.txt").readText()


fun main() {
    val input = readInput("day01")

    fun part1() {
        var dial = 50
        var answer = 0
        for (line in input) {
            val sign = if (line.startsWith("L")) -1 else +1
            val number = sign * line.substring(1).toInt()
            dial = (dial + number).mod(100)
            if (dial == 0) answer++
        }
        println(answer)
    }

    part1()
    var dial = 50
    var answer = 0
    // d = 1, n = L115 -> +2
    // d = 20, n = L115 -> -1

    // d = 1, n = R180 -> +1
    // d = 20, n = R180 -> +2
    for (line in input) {
        val sign = if (line.startsWith("L")) -1 else +1
        val number = sign * line.substring(1).toInt()
        val sum = dial + number
        dial = sum.mod(100)
        if (sum > 0) {
            answer += sum / 100
        } else {
            answer += (-sum + 99) / 100
        }
    }
    println(answer)

}
