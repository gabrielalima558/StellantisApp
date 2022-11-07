package com.example.stellantiscarapp.api

import com.example.stellantiscarapp.api.model.ChargingStation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.Retrofit

class ChargingStationApi(private val retrofit: Retrofit) {
    private val service: ChargingStationService by lazy {
        retrofit.create(ChargingStationService::class.java)
    }

    fun getChargingStations(): Flow<List<ChargingStation>> {
        return channelFlow {
            send(service.getChargingStation())
        }
    }
}