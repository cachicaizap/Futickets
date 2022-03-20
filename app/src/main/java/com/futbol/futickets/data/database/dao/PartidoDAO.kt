package com.futbol.futickets.data.database.dao

import androidx.room.*
import com.futbol.futickets.data.database.entidades.PartidoEntity

@Dao
interface PartidoDAO {

    @Query("SELECT * FROM partido")
    suspend fun getAllPartidos(): List<PartidoEntity>

    @Query("SELECT * FROM partido WHERE id = :partid")
    suspend fun getPartidoById(partid:String):PartidoEntity

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPartido(partido: PartidoEntity)

    @Update
    suspend fun updatePartido(partido: PartidoEntity)

    @Delete
    suspend fun deletePartido(partido: PartidoEntity)

    @Query("DELETE FROM partido WHERE id = :partid")
    suspend fun deletePartidoById(partid: String)
}