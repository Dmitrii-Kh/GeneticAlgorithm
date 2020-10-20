package com.ai.labs.domain

data class Course(val number: String, val name: String,
                  val instructors: Array<Instructor>, val maxNumbOfStudents: Int) {
    override fun toString(): String {
        return name
    }
}