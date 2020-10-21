package com.ai.labs

const val POPULATION_SIZE = 9
const val MUTATION_RATE = 0.1
const val CROSSOVER_RATE = 0.9
const val TOURNAMENT_SELECTION_SIZE = 3
const val NUMB_OF_ELITE_SCHEDULES = 1

fun main() {
    val driver = Driver()
    driver.data = Data()
    var generationNumber = 0
    driver.printAvailableData()
    println("> Generation # $generationNumber")
    print("  Schedule # |                                             ")
    print("Classes [dept,class,room, instructor, meeting-time]        ")
    println("                                                         | Fitness | Conflicts")
    print("-------------------------------------------------------------------------------------------------")
    println("----------------------------------------------------------------------------------------------------")
    val geneticAlgorithm = GeneticAlgorithm(driver.data)
    var population = Population(POPULATION_SIZE, driver.data).sortByFitness()
    population.schedules.forEach{ schedule -> println("         ${driver.scheduleNumb++} " +
            "  | $schedule | " + String.format("%.5f", schedule.fitness) + " |  ${schedule.numbOfConflicts}")}
    driver.printScheduleAsTable(population.schedules[0], generationNumber)
    driver.classNumb = 1
    while (population.schedules[0].fitness != 1.0) {
        println("> Generation # ${++generationNumber}")
        print("  Schedule # |                                             ")
        print("Classes [dept,class,room, instructor, meeting-time]        ")
        println("                                                         | Fitness | Conflicts")
        print("-------------------------------------------------------------------------------------------------")
        println("----------------------------------------------------------------------------------------------------")
        population = geneticAlgorithm.evolve(population).sortByFitness()
        driver.scheduleNumb = 0
        population.schedules.forEach{ schedule -> println("         ${driver.scheduleNumb++} " +
                "  | $schedule | " + String.format("%.5f", schedule.fitness) + " |  ${schedule.numbOfConflicts}")}
        driver.printScheduleAsTable(population.schedules[0], generationNumber)
        driver.classNumb = 1
    }
}

class Driver {
    lateinit var data: Data
    var scheduleNumb: Int = 0
    var classNumb: Int = 1

    fun printAvailableData() {
        println("Available Departments:")
        data.depts.forEach{ x -> println("name: ${x.name}, courses: ${x.courses}")}
        println("\nAvailable Courses:")
        data.courses.forEach{ x -> println("course #: ${x.number}, name: ${x.name}, max # of students: ${x.maxNumbOfStudents}, instructors: ${x.instructors}")}
        println("Available Rooms:")
        data.rooms.forEach{ x -> println("room #: ${x.number}, max seating capacity: ${x.seatingCapacity}")}
        println("Available Instructors:")
        data.instructors.forEach{ x -> println("id: ${x.id}, name: ${x.name}")}
        println("Available Meeting Times:")
        data.meetingTimes.forEach{ x -> println("id #: ${x.id}, Meeting Time: ${x.time}")}
    }

    fun printScheduleAsTable(schedule: Schedule, generation: Int) {
        val classes = schedule.classes
        print("\n                        ")
        println("Class # | Dept | Course (number, max # of students) | Room (Capacity) | Instructor (Id)      | Meeting Time (id) ")
        print("                       ")
        print("-----------------------------------------------------------------------------------")
        println("--------------------------------------")
        classes.forEach { x ->
            run {
                val majorIndex = data.depts.indexOf(x.dept)
                val courseIndex = data.courses.indexOf(x.course)
                val roomIndex = data.rooms.indexOf(x.room)
                val instructorIndex = data.instructors.indexOf(x.instructor)
                val meetingTimeIndex = data.meetingTimes.indexOf(x.meetingTime)
                print("                           ")
                print(String.format(" %1$02d ", classNumb) + " | ")
                print(String.format("%1$4s", data.depts[majorIndex].name) + " | ")
                print(String.format("%1$21s", data.courses[courseIndex].name) +
                        "(${data.courses[courseIndex].number} ${x.course.maxNumbOfStudents})       |")
                print(String.format("%1$10s", data.rooms[roomIndex].number) +
                        " (${x.room.seatingCapacity})  | ")
                print(String.format("%1$15s", data.instructors[instructorIndex].name) +
                        "(${data.instructors[instructorIndex].id})  | ")
                println(data.meetingTimes[meetingTimeIndex].time + " (${data.meetingTimes[meetingTimeIndex].id})")
                classNumb++
            }
        }
        if (schedule.fitness == 1.0)
            println("> Solution Found in ${generation+1} generations")
        print("-------------------------------------------------------------------------------------------------")
        println("----------------------------------------------------------------------------------------------------")
    }
}