package com.ai.labs

import com.ai.labs.domain.*

class Data() {
    public val rooms: ArrayList<Room>
    public val instructors: ArrayList<Instructor>
    public val courses: ArrayList<Course>
    public val depts: ArrayList<Department>
    public val meetingTimes: ArrayList<MeetingTime>
    var numOfClasses: Int = 0

    init {          // TODO change initialization
        val room1 = Room("R1", 25)
        val room2 = Room("R2", 45)
        val room3 = Room("R3", 35)
        rooms = arrayListOf(room1, room2, room3)

        val meetingTime1 = MeetingTime("MT1", "MWF 9:00 - 10:00")
        val meetingTime2 = MeetingTime("MT2", "MWF 10:00 - 11:00")
        val meetingTime3 = MeetingTime("MT1", "MWF 9:00 - 10:30")
        val meetingTime4 = MeetingTime("MT1", "MWF 10:30 - 12:00")
        meetingTimes = arrayListOf(meetingTime1, meetingTime2, meetingTime3, meetingTime4)

        val instructor1 = Instructor("I1", "Ivan Petrov")
        val instructor2 = Instructor("I2", "Alex Zen")
        val instructor3 = Instructor("I3", "Bob Lee")
        val instructor4 = Instructor("I4", "Sam White")
        instructors = arrayListOf(instructor1, instructor2, instructor3, instructor4)

        val course1 = Course("C1", "325K", arrayListOf(instructor1, instructor2), 25)
        val course2 = Course("C2", "319K", arrayListOf(instructor1, instructor2, instructor3), 35)
        val course3 = Course("C3", "462K", arrayListOf(instructor1, instructor2), 25)
        val course4 = Course("C4", "464K", arrayListOf(instructor3, instructor4), 30)
        val course5 = Course("C5", "360C", arrayListOf(instructor4), 35)
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