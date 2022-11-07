package com.example.stellantiscarapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stellantiscarapp.database.CarDao

class DetailCarViewModelFactory(
    private val dataSource: CarDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailCarViewModel::class.java)) {
            return DetailCarViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewHolder class")
    }
}