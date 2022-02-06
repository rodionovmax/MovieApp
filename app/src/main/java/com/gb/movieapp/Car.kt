package com.gb.movieapp

data class Car(val brand: String, val model: String, val year: Int) {

    fun convertToString(): String {
        return "$brand $model $year"
    }
}


