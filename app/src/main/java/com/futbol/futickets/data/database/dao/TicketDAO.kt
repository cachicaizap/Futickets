package com.futbol.futickets.data.database.dao

import androidx.room.*
import com.futbol.futickets.data.database.entidades.TicketEntity

@Dao
interface TicketDAO {
    @Query("SELECT * FROM tickets")
    suspend fun getAllTickets(): List<TicketEntity>

    @Query("SELECT * FROM tickets WHERE id = :idTickets")
    suspend fun getTicketsById(idTickets: String): TicketEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickets(tickets: TicketEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTickets(tickets: TicketEntity)

    @Delete()
    suspend fun deleteOneNews(tickets: TicketEntity)

    @Query("DELETE FROM tickets")
    suspend fun cleanDbTickets()

    @Query("DELETE FROM tickets WHERE id = :idTickets")
    suspend fun deleteTicketsById(idTickets: String)
}