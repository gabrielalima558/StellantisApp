package com.example.stellantiscarapp.listcars.adapter

import com.example.stellantiscarapp.database.Car


sealed class CarListItem(val name: String, val lastMaintenance: String) {
    data class Item(val car: Car) : CarListItem(car.carName, car.lastMaintenance)
    data class Separator(private val letter: Char) : CarListItem(letter.uppercaseChar().toString(), letter.uppercaseChar()
        .toString())
}