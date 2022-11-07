package com.example.stellantiscarapp.chargingstation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stellantiscarapp.api.ChargingStationApi
import com.example.stellantiscarapp.api.RetrofitBuilder

object ChargingStationViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val retrofit = RetrofitBuilder.retrofit

    private val chargingStationApi = ChargingStationApi(retrofit)


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChargingStationViewModel(
            chargingStationApi
        ) as T
    }
}