package com.ai.labs

import java.util.stream.IntStream

class Population {
    var schedules: ArrayList<Schedule> = ArrayList()
        get() {
            return field
        }
    constructor(size: Int, data: Data) {
        schedules = ArrayList<Schedule>(size)
        IntStream.range(0, size).forEach{ x -> schedules.add(Schedule(data).initialize()) }
    }
    fun sortByFitness(): Population {
        schedules.sortWith(Comparator { schedule1, schedule2 ->
            var returnVale = 0
            if (schedule1.fitness > schedule2.fitness)
                returnVale = -1
            else if (schedule1.fitness < schedule2.fitness)
                returnVale = 1
            returnVale
        })
        return this
    }

}