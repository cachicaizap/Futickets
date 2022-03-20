package com.futbol.futickets.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FutRetrofitApi {

    fun getFutApi(): Retrofit {
        return Retrofit.Builder().baseUrl("https://futbolapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}