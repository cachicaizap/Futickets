package com.futbol.futickets.data.api.service

import com.futbol.futickets.data.api.entidades.PartidoApiEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PartidoService {
    @GET("/games")
    suspend fun fetchGames(): Response<List<PartidoApiEntity>>

    @GET("/games/{id}")
    suspend fun fetchGame(@Path("id") id:String): Response<PartidoApiEntity>
}