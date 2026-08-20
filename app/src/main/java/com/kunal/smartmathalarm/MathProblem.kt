package com.kunal.smartmathalarm

data class MathProblem(
    val expression: String,
    val answer: Int,
    val difficulty: Int
)

object MathProblemGenerator {
    fun generate(difficulty: Int): MathProblem {
        return when (difficulty.coerceIn(1, 3)) {
            1 -> {
                val a = (2..9).random()
                val x = (2..9).random()
                val b = (1..12).random()
                MathProblem("${a}x + $b = ${a * x + b}", x, 1)
            }
            2 -> {
                val a = (2..8).random()
                val x = (2..9).random()
                val b = (3..15).random()
                MathProblem("${a}(x + $b) = ${a * (x + b)}", x, 2)
            }
            else -> {
                val a = (2..6).random()
                val b = (2..8).random()
                val x = (2..9).random()
                val c = (1..12).random()
                val result = a * (b * x - c) + 7
                MathProblem("${a}(${b}x - $c) + 7 = $result", x, 3)
            }
        }
    }
}
