package com.futbol.futickets.casosUso

import com.futbol.futickets.data.api.FutRetrofitApi
import com.futbol.futickets.data.api.entidades.toPartidoEntity
import com.futbol.futickets.data.api.service.PartidoService
import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.utils.Futicket

class PartidoUseCase {

    suspend fun getAllPartidos(): List<PartidoEntity>{
        var resp: MutableList<PartidoEntity> = ArrayList<PartidoEntity>()
        val service = FutRetrofitApi.getFutApi().create(PartidoService::class.java)
        val call = service.fetchGames()
        resp = if(call.isSuccessful){
            val body = call.body()
            body!!.map {
                it.toPartidoEntity()
            } as MutableList<PartidoEntity>
        }else{
            ArrayList<PartidoEntity>()
        }
        return resp
    }

    suspend fun getFavoritesPartidos(): List<PartidoEntity> {
        val db = Futicket.getDatabase()
        return db.partidoDao().getAllPartidos()
    }

    suspend fun savePartidoFavPart(partido: PartidoEntity) {
        Futicket.getDatabase().partidoDao().insertPartido(partido)
    }

    suspend fun deletePartidoFavPart(partido: PartidoEntity) {
        Futicket.getDatabase().partidoDao().deletePartidoById(partido.id)
    }

    suspend fun getOnePartido(id: String): PartidoEntity {
        return Futicket.getDatabase().partidoDao().getPartidoById(id)
    }
}