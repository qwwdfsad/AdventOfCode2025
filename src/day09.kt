import kotlin.apply
import kotlin.math.abs
import kotlin.math.max

fun main() {
//    val input = """7,1
//11,1
//11,7
//9,7
//9,5
//2,5
//2,3
//7,3""".lines()
    val input = readInput("day09")

    val corners = input.map { it.split(",").let { (x, y) -> x.toInt() to y.toInt() } }
    var area = 0L
    for (c1 in corners) {
        for (c2 in corners) {
            val dx = abs(c1.first - c2.first) + 1
            val dy = abs(c1.second - c2.second) + 1
            area = max(area, dx * dy.toLong())
        }
    }
    println(area)
}
