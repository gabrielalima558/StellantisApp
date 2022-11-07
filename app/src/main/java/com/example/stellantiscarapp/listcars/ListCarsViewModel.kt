package com.example.stellantiscarapp.listcars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.stellantiscarapp.database.Car
import com.example.stellantiscarapp.database.CarDao
import com.example.stellantiscarapp.listcars.adapter.CarListItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ListCarsViewModel(val dataSource: CarDao): ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val list: Flow<PagingData<CarListItem.Item>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        dataSource.allCars()
    }.flow
        .map { pagingData ->
            pagingData
                .map { car -> CarListItem.Item(car) }
        }
        .cachedIn(viewModelScope)

    fun startDeleteCar(car: Car) {
        uiScope.launch {
            deleteCar(car)
        }
    }

    private suspend fun deleteCar(car: Car) {
        withContext(Dispatchers.IO) {
            dataSource.deleteItem(car)

        }
    }
}