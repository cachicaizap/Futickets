package com.futbol.futickets.entidades

import java.util.*

data class Usuario(var id: String = "-1L", var firstname:String = "",
                   var lastname:String = "", var username:String = "",
                   var email:String = "", var password:String = ""){

    constructor(firstname: String, lastname: String,
                username: String, email: String, password: String) : this() {
        this.firstname = firstname
        this.lastname = lastname
        this.username = username
        this.email = email
        this.password = password
        this.id = UUID.randomUUID().toString()
    }
}
