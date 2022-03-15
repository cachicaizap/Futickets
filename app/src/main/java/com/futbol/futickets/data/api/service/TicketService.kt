package com.futbol.futickets.data.api.service

import com.futbol.futickets.data.api.entidades.ticketApi.TicketsApiEntity
import retrofit2.Response
import retrofit2.http.GET

interface TicketService {
    @GET("/tickets")
    suspend fun fetchTickets(): Response<TicketsApiEntity>
}