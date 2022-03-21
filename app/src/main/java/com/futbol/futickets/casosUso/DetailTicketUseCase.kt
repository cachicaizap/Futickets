package com.futbol.futickets.casosUso

import com.futbol.futickets.data.api.FutRetrofitApi
import com.futbol.futickets.data.api.entidades.toDetailTicketEntity
import com.futbol.futickets.data.api.service.PartidoService
import com.futbol.futickets.data.database.entidades.DetailTicketEntity

class DetailTicketUseCase {
    suspend fun getDetailOfPartido(id: String): List<DetailTicketEntity>{
        var resp: MutableList<DetailTicketEntity> = ArrayList<DetailTicketEntity>()
        val service = FutRetrofitApi.getFutApi().create(PartidoService::class.java)
        val call = service.fetchGame(id)
        resp = if(call.isSuccessful){
            val body = call.body()
            body!!.detailticket.map {
                it.toDetailTicketEntity()
            } as MutableList<DetailTicketEntity>
        }else{
            ArrayList<DetailTicketEntity>()
        }
        return resp
    }
}