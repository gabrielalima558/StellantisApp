package com.example.stellantiscarapp.api

import com.example.stellantiscarapp.api.model.ChargingStation
import retrofit2.http.GET

interface ChargingStationService {
    @GET("/api/eletroposto")
    suspend fun getChargingStation(): List<ChargingStation>
}