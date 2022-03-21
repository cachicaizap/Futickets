package com.futbol.futickets.logica

import com.futbol.futickets.casosUso.DetailTicketUseCase
import com.futbol.futickets.casosUso.PartidoUseCase
import com.futbol.futickets.data.database.entidades.DetailTicketEntity
import com.futbol.futickets.data.database.entidades.PartidoEntity

class DetailTicketBL {
    suspend fun getDetailFromPartido(id: String): List<DetailTicketEntity> {
        return DetailTicketUseCase().getDetailOfPartido(id)
    }
}