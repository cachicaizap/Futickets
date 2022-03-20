package com.futbol.futickets.data.api.entidades

import com.futbol.futickets.data.database.entidades.PartidoEntity

data class PartidoApiEntity(
    val avaible: Boolean,
    val dateat: String,
    val detailticket: List<Detailticket>,
    val id: String,
    val imgone: String,
    val imgtwo: String,
    val rounds: String,
    val stadium: String,
    val stadiumimg: String,
    val teamone: String,
    val teamtwo: String,
    val timeat: String
)

fun PartidoApiEntity.toPartidoEntity() = PartidoEntity(id,teamone,imgone,teamtwo,imgtwo,rounds,avaible,dateat,timeat,stadium,stadiumimg)