package com.ai.labs

import com.ai.labs.domain.*

const val POPULATION_SIZE = 9
const val MUTATION_RATE = 0.1
const val CROSSOVER_RATE = 0.9
const val TOURNAMENT_SELECTION_SIZE = 3
const val NUMB_OF_ELITE_SCHEDULES = 1

fun main() {
    val driver = Driver()
    driver.data = Data()
}

public class Driver {
     lateinit var data: Data

}