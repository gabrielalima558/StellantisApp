package com.example.stellantiscarapp.chargingstation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stellantiscarapp.api.ChargingStationApi
import com.example.stellantiscarapp.api.model.ChargingStation
import kotlinx.coroutines.launch

class ChargingStationViewModel(private val api: ChargingStationApi): ViewModel() {
    private var _chargingStation = MutableLiveData<List<ChargingStation>>()
    val chargingStation: LiveData<List<ChargingStation>> get() = _chargingStation

    fun getChargingStation() {
        viewModelScope.launch {
            api.getChargingStations().collect {
                _chargingStation.value = it
            }
        }
    }
}