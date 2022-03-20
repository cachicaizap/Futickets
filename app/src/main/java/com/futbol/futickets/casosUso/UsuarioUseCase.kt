package com.futbol.futickets.casosUso

import com.futbol.futickets.data.database.entidades.UsuarioEntity

class UsuarioUseCase {

    private val usuariosDb = listOf<UsuarioEntity>(
        UsuarioEntity("1","Carlos","Chiciaza","cachicaizap","cachicaizap@gmail.com","12345")
    )

    fun getVerifiedUser(name:String , pass: String): UsuarioEntity {
        var user = UsuarioEntity()

        usuariosDb.forEach(){
            if (it.username == name && it.password == pass){
                user = it
            }
        }

        return user
    }
}