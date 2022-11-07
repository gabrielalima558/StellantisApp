package com.example.stellantiscarapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://eletroposto20221027133550.azurewebsites.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}