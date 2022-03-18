package com.futbol.futickets.controladores

import com.futbol.futickets.logica.UsuarioBL

class UsuarioController {

    fun LoginUser(name:String, pass: String): Boolean {
        return UsuarioBL().LoginUser(
            name,
            pass
        )
    }
}