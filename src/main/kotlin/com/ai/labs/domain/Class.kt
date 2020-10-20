package com.ai.labs.domain

data class Class(val id: Int, val dept: Department, val course: Course) {
    lateinit var instructor: Instructor
    lateinit var meetingTime: MeetingTime
    lateinit var room: Room
    override fun toString(): String {
        return "[${dept.name}, ${course.number}, ${room.number}, ${instructor.id}, ${meetingTime.id}]"
    }
    // TODO rethink toString()
}