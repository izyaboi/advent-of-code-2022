fun main() {
    fun part1(input: List<String>): Int {
        return input
            .split { line -> line.isBlank() }
            .map { listOfCalories -> listOfCalories.map { calorie -> calorie.toInt() }.sumOf { it } }
            .maxOf { it }
    }

    fun part2(input: List<String>): Int {
        return input.split { line -> line.isBlank() }
            .map { listOfCalories -> listOfCalories.map { calorie -> calorie.toInt() }.sumOf { it } }
            .sortedByDescending { it }
            .take(3)
            .sum()
    }


/*    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 0)*/

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> =
    fold(mutableListOf(mutableListOf<T>())) { acc, t ->
        if (predicate(t)) acc.add(mutableListOf())
        else acc.last().add(t)
        acc
    }.filterNot { it.isEmpty() }
