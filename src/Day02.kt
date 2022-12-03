fun main() {
    fun part1(input: List<String>): Int {
        var totalScore = 0
        input.forEach {
            val opponent = it.substringBefore(" ").mapToShape()
            val response = it.substringAfter(" ").mapToShape()
            totalScore += getPoints(opponent, response) + response.ordinal
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0
        input.forEach {
            val result = it.substringAfter(" ").mapToResult()
            totalScore +=  Result.fromInt(result.value).value+1
        }
        return totalScore
    }


    /*    // test if implementation meets criteria from the description, like:
        val testInput = readInput("Day01_test")
        check(part1(testInput) == 0)*/

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun getPoints(opponent: Shape, response: Shape): Int {
    return when (opponent) {
        Shape.NONE -> 0
        Shape.ROCK -> when (response) {
            Shape.NONE -> 0
            Shape.ROCK -> 3
            Shape.PAPER -> 6
            Shape.SCISSOR -> 0
        }

        Shape.PAPER -> when (response) {
            Shape.NONE -> 0
            Shape.ROCK -> 0
            Shape.PAPER -> 3
            Shape.SCISSOR -> 6
        }

        Shape.SCISSOR -> when (response) {
            Shape.NONE -> 0
            Shape.ROCK -> 6
            Shape.PAPER -> 0
            Shape.SCISSOR -> 3
        }
    }
}

enum class Shape(val value: Int) {
    NONE(0),
    ROCK(1),
    PAPER(2),
    SCISSOR(3);
    companion object {
        fun fromInt(value: Int) = Shape.values().first { it.value == value }
    }
}

enum class Result(val value: Int) {
    NONE(0),
    LOOSE(0),
    DRAW(3),
    WIN(6);
    companion object {
        fun fromInt(value: Int) = Result.values().first { it.value == value }
    }
}

fun String.mapToResult(): Result {
    return when {
        this == "Y" -> Result.DRAW
        this == "Z" -> Result.WIN
        this == "X" -> Result.LOOSE
        else -> Result.NONE
    }
}

fun String.mapToShape(): Shape {
    return when {
        this == "A" || this == "X" -> Shape.ROCK
        this == "B" || this == "Y" -> Shape.PAPER
        this == "C" || this == "Z" -> Shape.SCISSOR
        else -> Shape.NONE
    }
}
