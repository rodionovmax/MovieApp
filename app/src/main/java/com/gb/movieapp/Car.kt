package com.gb.movieapp

data class Car(val mark: String, val model: String, val year: Int) {

    fun convertToString(): String {
        return "$mark $model $year"
    }
}


