fun topSort(root: String, graph: HashMap<String, List<String>>): List<String> {
    val result = ArrayList<String>(graph.keys.size)
    val visited = HashSet<String>()
    fun topSort(curr: String) {
        if (!visited.add(curr)) return
        graph[curr]?.forEach {
            topSort(it)
        }
        result.add(curr)
    }
    topSort(root)
    return result.reversed()
}

fun main() {
//    val input = """svr: aaa bbb
//aaa: fft
//fft: ccc
//bbb: tty
//tty: ccc
//ccc: ddd eee
//ddd: hub
//hub: fff
//eee: dac
//dac: fff
//fff: ggg hhh
//ggg: out
//hhh: out""".lines()
//    val input = """
//        v1: e1 e2 e3 e4 e5
//        e1: v2
//        e2: v2
//        e3: v2
//        e4: v2
//        e5: v2
//        v2: ee1 ee2 ee3 ee4 ee5
//        ee1: v3
//        ee2: v3
//        ee3: v3
//        ee4: v3
//        ee5: v3
//    """.trimIndent().lines()
    val input = readInput("day11")

    val graph = HashMap<String, List<String>>()
    for (line in input) {
        val v = line.substringBefore(":")
        val ee = line.substringAfter(" ").split(" ")
        graph[v] = ee
    }
    val sorted = topSort("svr", graph)
    val start = "svr"
    val end = "out"
    val needles = hashSetOf("fft", "dac")

    graph[end] = emptyList()

    val dp = HashMap<String, LongArray>()
    graph.keys.forEach { dp[it] = longArrayOf(0, 0, 0) }
    dp[end] = longArrayOf(0, 0, 0)
    dp[start] = longArrayOf(1, 0, 0)

    for (v in sorted) {
         if (v in needles) {
            val cnt = dp.getValue(v)
            val slot = cnt.indexOfFirst { it == 0L }
            if (slot == -1) error("?")
            for (s in slot - 1 downTo 0) {
                cnt[s + 1] += cnt[s]
                cnt[s] = 0
            }
        }

         graph[v]?.forEach { to ->
            val counter = dp.getValue(to)
            counter[0] += dp.getValue(v)[0]
            counter[1] += dp.getValue(v)[1]
            counter[2] += dp.getValue(v)[2]
        }
    }
    println(dp.getValue(end).contentToString())
}
