package com.futbol.futickets.data.database.entidades

data class UsuarioEntity(
    var id: String = "-1L",
    var firstname:String = "",
    var lastname:String = "",
    var username:String = "",
    var email:String = "",
    var password:String = ""
)
