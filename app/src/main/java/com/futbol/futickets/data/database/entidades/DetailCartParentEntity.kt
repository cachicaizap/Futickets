package com.futbol.futickets.data.database.entidades

import kotlinx.serialization.Serializable

@Serializable
data class DetailCartParentEntity(
    var idparent:String = "",
    val imgstadium:String = "",
    val game:String = "",
    val dateat:String = "",
    var cost:Double = 0.0,
    var quantity:Int = 0
)
