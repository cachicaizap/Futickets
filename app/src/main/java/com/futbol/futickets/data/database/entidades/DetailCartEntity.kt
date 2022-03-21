package com.futbol.futickets.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "detail_cart")
@Serializable
data class DetailCartEntity(
    @PrimaryKey
    val id:String= "",
    val imgstadium:String = "",
    val game:String = "",
    val dateat:String = "",
    val timeat:String = "",
    val location:String = "",
    var cost:Double = 0.0,
    var quantity:Int = 0,
    var idparent:String = ""
)