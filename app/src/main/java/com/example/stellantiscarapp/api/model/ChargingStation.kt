package com.example.stellantiscarapp.api.model

data class ChargingStation(
    val id: Long,
    val nome: String,
    val informacoes: String,
    val endereco: String,
    val telefone: String,
    val conectores: List<String>
)