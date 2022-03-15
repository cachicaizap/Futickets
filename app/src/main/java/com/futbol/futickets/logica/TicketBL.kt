package com.futbol.futickets.logica

import com.futbol.futickets.casosUso.TicketsUseCase
import com.futbol.futickets.data.database.entidades.TicketEntity

class TicketBL {
    suspend fun getTicketsList(): List<TicketEntity> {
        return TicketsUseCase().getAllTicketsApi()
    }

    suspend fun checkIsSaved(id: String): Boolean {
        val n = TicketsUseCase().getOneTickets(id)
        return n != null
    }

    suspend fun saveTicketFavourite(tickets: TicketEntity) {
        TicketsUseCase().saveTicketFavourite(tickets)
    }

    suspend fun deleteTicketFavourite(tickets: TicketEntity) {
        TicketsUseCase().deleteTicketFavourite(tickets)
    }
}