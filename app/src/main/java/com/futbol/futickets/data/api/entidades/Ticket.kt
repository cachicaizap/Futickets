package com.futbol.futickets.data.api.entidades

import com.futbol.futickets.data.database.entidades.TicketEntity
import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("_id")
    val id: String,
    val team_one: String,
    val team_one_logo: String,
    val team_two: String,
    val team_two_logo: String,
    val price: Float,
    val quantity: Int,
    val rounds: String,
    val avaible: Boolean,
    val date_at: String,
    val time_at: String,
    val stadium: String
)

fun Ticket.toTicketEntity() = TicketEntity(
    id,
    team_one,
    team_one_logo,
    team_two,
    team_two_logo,
    price, quantity,
    rounds,
    avaible,
    date_at,
    time_at,
    stadium
)