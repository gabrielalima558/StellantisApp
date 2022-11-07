package com.example.stellantiscarapp.newcar

import androidx.lifecycle.ViewModel
import com.example.stellantiscarapp.database.Car
import com.example.stellantiscarapp.database.CarDao
import kotlinx.coroutines.*

class NewCarViewModel(val dataSource: CarDao): ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun startInsertCar(car: Car) {
        uiScope.launch {
            insertCar(car)
        }
    }

    private suspend fun insertCar(car: Car) {
        withContext(Dispatchers.IO) {
            dataSource.insertCar(car)

        }
    }
}