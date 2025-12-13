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
    val input = readInput("day11")

    val graph = HashMap<String, List<String>>()
    for (line in input) {
        val v = line.substringBefore(":")
        val ee = line.substringAfter(" ").split(" ")
        graph[v] = ee
    }
    val start = "svr"
    val end = "out"
    val needles = hashSetOf("fft", "dac")

    graph[end] = emptyList()

    val paths = HashMap<String, LongArray>()
    paths[start] = longArrayOf(1, 0, 0)

    val queue = ArrayDeque<String>()
    queue.add("svr")
    while (queue.isNotEmpty()) {
        val v = queue.removeFirst()
        // Check if we repeatedly visited the same v to propagate only once
        val counter = paths.remove(v) ?: continue
        if (v in needles) {
            val slot = counter.indexOfFirst { it == 0L }
            if (slot == -1) error("?")
            for (s in slot - 1 downTo 0) {
                counter[s + 1] += counter[s]
                counter[s] = 0
            }
        }
        graph[v]?.forEach { to ->
            val hadCounter = paths.contains(to)
            val toCounter = paths.getOrPut(to) { longArrayOf(0, 0, 0) }
            toCounter[0] += counter[0]
            toCounter[1] += counter[1]
            toCounter[2] += counter[2]
            // Don't have a counter -- wasn't propagated yet
            if (!hadCounter && to != "out") {
                queue.addLast(to)
            }
        }
    }
    // 517315308154944
    println(paths.getValue(end).contentToString())
}
