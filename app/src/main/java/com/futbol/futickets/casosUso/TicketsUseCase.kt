package com.futbol.futickets.casosUso

import com.futbol.futickets.data.api.RetrofitAPI
import com.futbol.futickets.data.api.entidades.toTicketEntity
import com.futbol.futickets.data.api.service.TicketService
import com.futbol.futickets.data.database.entidades.TicketEntity
import com.futbol.futickets.utils.Futicket

class TicketsUseCase {
    suspend fun getAllTicketsApi(): List<TicketEntity> {

        val service = RetrofitAPI.getFutApi().create(TicketService::class.java)
        val call = service.fetchTickets()
        val resp = if (call.isSuccessful) {
            return call.body()!!.articles.map {
                it.toTicketEntity()
            }
        } else (ArrayList<TicketEntity>())
        return resp
    }

    suspend fun saveTicketFavourite(tickets: TicketEntity) {
        Futicket.getDatabase().ticketDao().insertTickets(tickets)
    }

    suspend fun deleteTicketFavourite(tickets: TicketEntity) {
        Futicket.getDatabase().ticketDao().deleteTicketsById(tickets.id)
    }

    suspend fun getOneTickets(id: String): TicketEntity {
        return Futicket.getDatabase().ticketDao().getTicketsById(id)
    }
}