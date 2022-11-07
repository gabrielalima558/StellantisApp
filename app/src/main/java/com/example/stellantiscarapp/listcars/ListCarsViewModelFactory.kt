package com.example.stellantiscarapp.listcars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stellantiscarapp.database.CarDao

class ListCarsViewModelFactory(
    private val dataSource: CarDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCarsViewModel::class.java)) {
            return ListCarsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewHolder class")
    }
}