package com.ai.labs

import com.ai.labs.domain.*
import java.util.function.Consumer


class Schedule() {
    var classes: ArrayList<Class> = ArrayList()
        get() {
            isFitnessChanged = true
            return field
        }
    var data: Data = Data()
    private var classNumb = 0
    var numbOfConflicts = 0
    var fitness: Double = -1.0
        get() {
            if (isFitnessChanged) {
                field = calculateFitness()
                isFitnessChanged = false
            }
            return field
        }
    private var isFitnessChanged = true

    constructor(data: Data) : this() {
        this.data = data
        classes = ArrayList(data.numOfClasses)
    }

    fun initialize(): Schedule {
        ArrayList<Department>(data.depts).forEach(Consumer { dept: Department ->
            dept.courses.forEach { course ->
                val newClass = Class(classNumb++, dept, course)
                newClass.meetingTime = data.meetingTimes[(data.meetingTimes.size * Math.random()).toInt()]
                newClass.room = (data.rooms[(data.rooms.size * Math.random()).toInt()])
                newClass.instructor = course.instructors[(course.instructors.size * Math.random()).toInt()]
                classes.add(newClass)
            }
        })
        return this
    }

    private fun calculateFitness(): Double {
        numbOfConflicts = 0
        classes.forEach{ x ->
            if (x.room.seatingCapacity < x.course.maxNumbOfStudents)
                numbOfConflicts++
            classes.stream().filter{ y -> classes.indexOf(y) >= classes.indexOf(x) }.forEach { y ->
                run {
                    if (x.meetingTime == y.meetingTime && x.id != y.id) {
                        if (x.room == y.room) numbOfConflicts++
                        if (x.instructor == y.instructor) numbOfConflicts++
                    }
                }
            }
        }
        return (1/(numbOfConflicts + 1).toDouble())
    }

    override fun toString(): String {
        var returnValue = ""
        var x = 0
        while (x < classes.size-1){
            returnValue += classes[x].toString() + ","
            x++
        }
        returnValue += classes[classes.size-1]
        return returnValue
    }
    // TODO need refactoring

}

