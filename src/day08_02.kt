import kotlin.collections.sortedByDescending

fun main() {
//    val input = """162,817,812
//57,618,57
//906,360,560
//592,479,940
//352,342,300
//466,668,158
//542,29,236
//431,825,988
//739,650,466
//52,470,668
//216,146,977
//819,987,18
//117,168,530
//805,96,715
//346,949,466
//970,615,88
//941,993,340
//862,61,35
//984,92,344
//425,690,689""".lines()
    val input = readInput("day08")

    class Dsu {
        val components = IntArray(input.size) { it }
        val size = LongArray(input.size) { 1 }

        fun find(x: Int): Int {
            if (components[x] == x) return x
            components[x] = find(components[x])
            return components[x]
        }

        fun connect(l1: Int, l2: Int) {
            val p1 = find(l1)
            val p2 = find(l2)
            if (p1 == p2) return
            if (size[p1] > size[p2]) {
                components[p2] = p1
                size[p1] += size[p2]
            } else {
                components[p1] = p2
                size[p2] += size[p1]
            }

        }

        fun connected(l1: Int, l2: Int) = find(l1) == find(l2)
    }

    val dsu = Dsu()
    val junctions = input.map { it.split(",").map { it.toLong() } }.map { l -> LongArray(3) { l[it] } }

    val edges = ArrayList<Triple<Int, Int, Long>>()
    for (i1 in junctions.indices) {
        for (i2 in i1 + 1..<junctions.size) {
            val j1 = junctions[i1]
            val j2 = junctions[i2]
            val d =
                (j1[0] - j2[0]) * (j1[0] - j2[0]) + (j1[1] - j2[1]) * (j1[1] - j2[1]) + (j1[2] - j2[2]) * (j1[2] - j2[2])
            edges += Triple(i1, i2, d)
        }
    }

    edges.sortedBy { it.third }.takeWhile {
        dsu.connect(it.first, it.second)
        if (dsu.size[dsu.find(it.first)] == junctions.size.toLong()) {
            println(it.first.toString() + " " + it.second)
            println(junctions[it.first][0] * junctions[it.second][0])
            false
        } else {
            true
        }
    }
}

