package com.gb.movieapp

/*
* Easy implementation for getting singleton in Kotlin
* */
object Repo {

    private val carsList : List<Car>

    init {
        carsList = listOf(
            Car("Lamborghini", "Huracán", 2020),
            Car("Aston Martin", "Valkyrie", 2021),
            Car("Lada", "Priora", 2010)
        )
    }

    fun getCarsList() : List<Car> {
        return carsList
    }

    private val car = Car("Porsche", "Boxter", 2020)
    private val copy = car.copy(model = "911 GT3 RS")

    fun getCar() : Car {
        return copy
    }
}