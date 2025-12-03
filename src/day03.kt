fun main() {
  val input = readInput("day03")
//    val input = """987654321111111
//811111111111119
//234234234234278
//818181911112111""".lines()

    var result = 0L
    for (line in input) {
        val digits = line.map { it - '0' }
        var mi = 0
        for (i in 0..<digits.size - 1) {
            if (digits[i] > digits[mi]) {
                mi = i
            }
        }
        var m1 = digits[mi]
        var m2 = 0
        for (i in mi + 1..<digits.size) {
            m2 = maxOf(m2, digits[i])
        }
        result += m1 * 10 + m2
    }
    println(result)


    result = 0L
    for (line in input) {
        val digits = line.map { it - '0' }
        var batteries = 12
        var start = 0
        var sum = 0L
        repeat(batteries) {
            var max = start
            val trailingEnd = digits.size - (batteries - it - 1)
            for (i in start until trailingEnd) {
                if (digits[i] > digits[max]) {
                    max = i
                }
            }
            sum = sum * 10 + digits[max]
            start = max + 1
        }
        result += sum
    }
    println(result)
}
