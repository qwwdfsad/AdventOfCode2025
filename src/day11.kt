fun main() {
//    val input = """aaa: you hhh
//you: bbb ccc
//bbb: ddd eee
//ccc: ddd eee fff
//ddd: ggg
//eee: out
//fff: out
//ggg: out
//hhh: ccc fff iii
//iii: out""".lines()
    val input = readInput("day11")

    val graph = HashMap<String, List<String>>()
    for (line in input) {
        val v = line.substringBefore(":")
        val ee = line.substringAfter(" ").split(" ")
        graph[v] = ee
    }

    val dp = HashMap<String, Int>()
    val queue = ArrayDeque<String>(graph.getValue("you"))
    while (!queue.isEmpty()) {
        val v = queue.removeLast()
        dp.compute(v) { _, v -> if (v == null) 1 else v + 1}
        if (v != "out") queue.addAll(graph.getValue(v))
    }
    println(dp["out"])
}
