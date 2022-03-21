package com.futbol.futickets.data.database.entidades

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class DetailTicketEntity(
    @PrimaryKey
    val id:String = "",
    val cost: Double = 0.0,
    val location: String = "",
    val quantity: Int = 0
)
