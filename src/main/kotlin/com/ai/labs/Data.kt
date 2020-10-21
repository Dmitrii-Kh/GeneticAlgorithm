package com.ai.labs

import com.ai.labs.domain.*

class Data {
    val rooms: ArrayList<Room>
    val instructors: ArrayList<Instructor>
    val courses: ArrayList<Course>
    val depts: ArrayList<Department>
    val meetingTimes: ArrayList<MeetingTime>
    var numOfClasses: Int = 0

    init {
        val room1 = Room("R1", 25)
        val room2 = Room("R2", 45)
        val room3 = Room("R3", 50)
        rooms = arrayListOf(room1, room2, room3)

        val meetingTime1 = MeetingTime("MT1", "MWF 8:30 - 9:50")
        val meetingTime2 = MeetingTime("MT2", "MWF 10:00 - 11:40")
        val meetingTime3 = MeetingTime("MT3", "TTH 8:30 - 9:50")
        val meetingTime4 = MeetingTime("MT4", "TTH 10:00 - 11:40")
        meetingTimes = arrayListOf(meetingTime1, meetingTime2, meetingTime3, meetingTime4)

        val instructor1 = Instructor("I1", "Ivan Petrov")
        val instructor2 = Instructor("I2", "Alex Zen")
        val instructor3 = Instructor("I3", "Bob Lee")
        val instructor4 = Instructor("I4", "Sam White")
        instructors = arrayListOf(instructor1, instructor2, instructor3, instructor4)

        val course1 = Course("C1", "325P1", arrayListOf(instructor1, instructor2), 25)
        val course2 = Course("C2", "319P2", arrayListOf(instructor1, instructor2, instructor3), 35)
        val course3 = Course("C3", "325P2", arrayListOf(instructor1, instructor2), 25)
        val course4 = Course("C4", "319P1", arrayListOf(instructor3, instructor4), 30)
        val course5 = Course("C5", "319L", arrayListOf(instructor4), 50)
        val course6 = Course("C6", "303K", arrayListOf(instructor1, instructor3), 45)
        val course7 = Course("C7", "303L", arrayListOf(instructor2, instructor4), 35)
        courses = arrayListOf(course1, course2, course3, course4, course5, course6, course7)

        val dept1 = Department("MATH", arrayListOf(course1, course3))
        val dept2 = Department("EE", arrayListOf(course2, course4, course5))
        val dept3 = Department("PHY", arrayListOf(course6, course7))
        depts = arrayListOf(dept1, dept2, dept3)
        depts.forEach{ x -> numOfClasses += x.courses.size }
    }
}