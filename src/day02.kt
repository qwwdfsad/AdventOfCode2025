fun main() {
    val input = readInput("day02")[0].split(",")

    // 01
    var answer = 0L
    for (range in input) {
        val start = range.substringBefore("-").toLong()
        val end = range.substringAfter("-").toLong()
        for (id in start..end) {
            val str = id.toString()
            if (str.length % 2 == 1) continue
            if (str.substring(0, str.length / 2) == str.substring(str.length / 2)) {
                answer += id
            }
        }
    }
    println(answer)

    // 02
    answer = 0L
    for (range in input) {
        val start = range.substringBefore("-").toLong()
        val end = range.substringAfter("-").toLong()
        for (id in start..end) {
            val str = id.toString()
            for (len in 0 until str.length / 2) {
                val substr = str.substring(0, len + 1)
                if (substr.repeat(str.length / (len + 1)) == str) {
                    answer += id
                    break
                }
            }
        }
    }
    println(answer)
}
