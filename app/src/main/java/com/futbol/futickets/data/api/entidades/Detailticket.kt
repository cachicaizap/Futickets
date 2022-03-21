package com.futbol.futickets.data.api.entidades

import com.futbol.futickets.data.database.entidades.DetailTicketEntity

data class Detailticket(
    val id: String,
    val cost: Double,
    val location: String,
    val quantity: Int
)

fun Detailticket.toDetailTicketEntity() = DetailTicketEntity(id,cost,location,quantity)