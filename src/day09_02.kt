import kotlin.math.abs
import kotlin.math.max

val DOT = '.'.code.toByte()
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

    data class Point(val x: Int, val y: Int)

    val corners = input.map { it.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) } }
    val maxY = corners.maxOf { it.y } + 1
    val maxX = corners.maxOf { it.x } + 1
    val grid = Array(maxY + 1) { ByteArray(maxX + 1) { DOT } }
    fun printGrid() = grid.forEach { println(it.map { it.toChar() }.joinToString("")) }
    corners.forEach { grid[it.y][it.x] = '#'.code.toByte() }

    val edges = corners.zipWithNext().toMutableList().also { it.add(corners.last() to corners.first()) }
    edges.forEach { (p1, p2) ->
        if (p1.x == p2.x) {
            val y1 = minOf(p1.y, p2.y)
            val y2 = maxOf(p1.y, p2.y)
            for (y in y1 + 1..<y2) grid[y][p1.x] = '|'.code.toByte()
        } else {
            require(p1.y == p2.y)
            grid[p1.y].fill('-'.code.toByte(), minOf(p1.x, p2.x) + 1, maxOf(p1.x, p2.x))
        }
    }

//    printGrid()
    grid.forEach { row ->
        var inLoop = false
        for (i in 1 until row.size - 1) {
            if (inLoop && row[i] == DOT) row[i] = 'X'.code.toByte()
            if (!inLoop && (row[i] == '|'.code.toByte() || row[i] == '#'.code.toByte())) inLoop = true
            else if (inLoop && row[i] == '|'.code.toByte()) inLoop = false
        }
    }

//    printGrid()

    var area = 0L
    var i = 0
    for (c1 in corners) {
        println("${++i} of ${corners.size}")
        for (c2 in corners) {
            val dx = abs(c1.x - c2.x) + 1
            val dy = abs(c1.y - c2.y) + 1
            val startX = minOf(c1.x, c2.x)
            val startY = minOf(c1.y, c2.y)
            var applicable = true
            for (x in startX..<startX + dx) {
                if (grid[startY][x] == DOT || grid[startY + dy - 1][x] == DOT) {
                    applicable = false
                    break
                }
            }
            if (!applicable) continue
            for (y in startY..<startY + dy) {
                if (grid[y][startX] == DOT || grid[y][startX + dx - 1] == DOT) {
                    applicable = false
                    break
                }
            }
            if (!applicable) continue
            area = max(area, dx * dy.toLong())
        }
    }
    println(area)
}

