package com.example.stellantiscarapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stellantiscarapp.database.Car
import com.example.stellantiscarapp.database.CarDao
import kotlinx.coroutines.*

class DetailCarViewModel(val dataSource: CarDao) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _car = MutableLiveData<Car>()
    val car: LiveData<Car> get() = _car

    fun startGetCar(name: String) {
        uiScope.launch {
            _car.value = getCar(name)
        }
    }

    private suspend fun getCar(nameCar: String): Car {
        var car: Car
        withContext(Dispatchers.IO) {
            car = dataSource.getCar(nameCar) as Car
        }
        return car
    }

    fun startUpdateCar(car: Car) {
        uiScope.launch {
            updateCar(car)
        }
    }

    private suspend fun updateCar(car: Car) {
        withContext(Dispatchers.IO) {
            dataSource.updateItem(car)
        }
    }


}