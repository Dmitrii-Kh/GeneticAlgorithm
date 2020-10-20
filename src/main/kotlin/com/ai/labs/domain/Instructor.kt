package com.ai.labs.domain

data class Instructor(val id: String, val name: String) {
    override fun toString(): String {
        return name
    }
}