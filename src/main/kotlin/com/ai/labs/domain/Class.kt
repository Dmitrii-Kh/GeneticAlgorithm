package com.ai.labs.domain

data class Class(val id: Int, val dept: Department, val course: Course,
                 var instructor: Instructor, var meetingTime: MeetingTime, var room: Room) {
    override fun toString(): String {
        return "[${dept.name}, ${course.number}, ${room.number}, ${instructor.id}, ${meetingTime.id}]"
    }
    // TODO rethink toString()
}