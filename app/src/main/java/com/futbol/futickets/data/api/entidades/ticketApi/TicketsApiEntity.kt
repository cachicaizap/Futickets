package com.futbol.futickets.data.api.entidades.ticketApi

import com.futbol.futickets.data.api.entidades.Ticket

data class TicketsApiEntity(
    val articles: List<Ticket>,
    val status: String,
    val totalResults: Int
)
