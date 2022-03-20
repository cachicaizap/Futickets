package com.futbol.futickets.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "partido")
@Serializable
data class PartidoEntity(
    @PrimaryKey
    var id:String = "-1L",
    var teamone:String? = "",
    var imgone:String? = "",
    var teamtwo:String? = "",
    var imgtwo:String? = "",
    var rounds:String? = "",
    var avaible:Boolean? = false,
    var dateat: String? = "",
    var timeat:String? = "",
    var stadium:String? = "",
    var stadiumimg:String? = ""
)
