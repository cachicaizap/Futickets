package com.futbol.futickets.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    fun getFutApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://9196-191-99-140-81.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}