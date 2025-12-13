
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
    val needles = hashSetOf("fft", "dac")

    // Calculate the outbound: dfs(v) == x means that there is x paths to 'out' from v
    val result = hashMapOf<String, Long>()
    fun dfs(v: String, mask: Int): Long {
        if (v == "out") return if (mask == 2) 1L else 0L
        result[v + mask]?.let { return it }
        val vs = graph.getValue(v)
        var answer = 0L
        for (v in vs) {
            answer += dfs(v, mask + if (v in needles) 1 else 0)
        }
        result[v + mask] = answer
        return answer
    }
    println(dfs("svr", 0))
}
