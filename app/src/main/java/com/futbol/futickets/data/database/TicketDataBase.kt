package com.futbol.futickets.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.futbol.futickets.data.database.dao.TicketDAO
import com.futbol.futickets.data.database.entidades.TicketEntity

@Database(
    entities = [TicketEntity::class],
    version = 1
)
abstract class TicketDataBase : RoomDatabase() {
    abstract fun ticketDao(): TicketDAO
}