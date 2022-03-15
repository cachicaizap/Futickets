package com.futbol.futickets.logica

import com.futbol.futickets.casosUso.UsuarioUseCase

class UsuarioBL {

    fun LoginUser(name:String, pass: String) :Boolean {
        var us = UsuarioUseCase().getVerifiedUser(name,pass)
        return (us.id == "-1L")
    }
}