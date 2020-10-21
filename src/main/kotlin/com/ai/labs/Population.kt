package com.ai.labs

import java.util.stream.IntStream

class Population(size: Int, data: Data) {
    var schedules: ArrayList<Schedule> = ArrayList()
        get() {
            return field
        }

    init {
        schedules = ArrayList<Schedule>(size)
        IntStream.range(0, size).forEach{ _ -> schedules.add(Schedule(data).initialize()) }
    }
    fun sortByFitness(): Population {
        schedules.sortWith { schedule1, schedule2 ->
            var returnVale = 0
            if (schedule1.fitness > schedule2.fitness)
                returnVale = -1
            else if (schedule1.fitness < schedule2.fitness)
                returnVale = 1
            returnVale
        }
        return this
    }

}