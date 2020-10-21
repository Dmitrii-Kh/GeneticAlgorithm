package com.ai.labs

import java.util.stream.IntStream

data class GeneticAlgorithm(val data: Data){
    public fun evolve(population: Population): Population {
        return mutatePopulation(crossoverPopulation(population))
    }

    private fun crossoverPopulation(population: Population): Population {
        val crossoverPopulation = Population(population.schedules.size, data)
        IntStream.range(0, NUMB_OF_ELITE_SCHEDULES).forEach{ x ->
            crossoverPopulation.schedules[x] = population.schedules[x] }
        IntStream.range(NUMB_OF_ELITE_SCHEDULES, population.schedules.size).forEach{ x ->
            if (CROSSOVER_RATE > Math.random()) {
                val schedule1 = selectTournamentPopulation(population).sortByFitness().schedules[0]
                val schedule2 = selectTournamentPopulation(population).sortByFitness().schedules[0]
                crossoverPopulation.schedules[x] = crossoverSchedule(schedule1, schedule2)
            } else crossoverPopulation.schedules[x] = population.schedules[x]
        }
        return crossoverPopulation
    }
    fun crossoverSchedule(schedule1: Schedule, schedule2: Schedule): Schedule {
        val crossoverSchedule = Schedule(data).initialize()
        IntStream.range(0, crossoverSchedule.classes.size).forEach { x ->
            if (Math.random() > 0.5) crossoverSchedule.classes[x] = schedule1.classes[x]
            else crossoverSchedule.classes[x] = schedule2.classes[x]
        }
        return crossoverSchedule
    }
    private fun mutatePopulation(population: Population): Population {
        val mutatePopulation = Population(population.schedules.size, data)
        val schedules = mutatePopulation.schedules
        IntStream.range(0, NUMB_OF_ELITE_SCHEDULES).forEach { x ->
            schedules[x] = population.schedules[x]
        }
        IntStream.range(NUMB_OF_ELITE_SCHEDULES, population.schedules.size).forEach { x ->
            schedules[x] = mutateSchedule(population.schedules[x])
        }
        return mutatePopulation
    }

    private fun mutateSchedule(mutateSchedule: Schedule): Schedule {
        val schedule = Schedule(data).initialize()
        IntStream.range(0, mutateSchedule.classes.size).forEach { x ->
            if (MUTATION_RATE > Math.random()) mutateSchedule.classes[x] = schedule.classes[x]
        }
        return mutateSchedule
    }
    private fun selectTournamentPopulation(population: Population): Population {
        val tournamentPopulation = Population(TOURNAMENT_SELECTION_SIZE, data)
        IntStream.range(0, TOURNAMENT_SELECTION_SIZE).forEach { x ->
            tournamentPopulation.schedules[x] = population.schedules[(population.schedules.size * Math.random()).toInt()]
        }
        return tournamentPopulation
    }
}