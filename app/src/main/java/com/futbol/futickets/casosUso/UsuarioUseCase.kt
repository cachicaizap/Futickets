package com.futbol.futickets.casosUso

import com.futbol.futickets.entidades.Usuario

class UsuarioUseCase {

    private val usuariosDb = listOf<Usuario>(
        Usuario("Carlos","Chiciaza","cachicaizap","cachicaizap@gmail.com","12345")
    )

    fun getVerifiedUser(name:String , pass: String): Usuario {
        var user = Usuario()

        usuariosDb.forEach(){
            if (it.username == name && it.password == pass){
                user = it
            }
        }

        return user
    }
}