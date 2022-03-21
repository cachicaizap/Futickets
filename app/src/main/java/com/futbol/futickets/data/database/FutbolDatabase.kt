package com.futbol.futickets.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.futbol.futickets.data.database.dao.DetailCartDAO
import com.futbol.futickets.data.database.dao.PartidoDAO
import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.data.database.entidades.PartidoEntity

@Database(
    entities = [PartidoEntity::class, DetailCartEntity::class],
    version = 1
)

abstract class FutbolDatabase : RoomDatabase() {

    abstract fun partidoDao(): PartidoDAO

    abstract  fun detailCartDAO(): DetailCartDAO
}