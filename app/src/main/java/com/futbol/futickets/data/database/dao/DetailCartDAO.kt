package com.futbol.futickets.data.database.dao

import androidx.room.*
import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.data.database.entidades.DetailCartParentEntity

@Dao
interface DetailCartDAO {
    @Query("SELECT * FROM detail_cart")
    suspend fun getAllDetailCart(): List<DetailCartEntity>

    @Query("SELECT * FROM detail_cart WHERE id = :detid")
    suspend fun getDetailCartById(detid: String): DetailCartEntity

    @Query("SELECT * FROM detail_cart WHERE idparent = :detid")
    suspend fun getDetailCartByIdParent(detid: String): List<DetailCartEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertDetailCart(detail: DetailCartEntity)

    @Update
    suspend fun updateDetailCart(detail: DetailCartEntity)

    @Delete
    suspend fun deleteDetailCart(detail: DetailCartEntity)

    @Query("DELETE FROM detail_cart WHERE id = :detid")
    suspend fun deleteDetailCartById(detid: String)

    @Query("SELECT idparent, imgstadium, game, dateat, sum(quantity) AS quantity, sum(cost) AS cost FROM detail_cart GROUP BY idparent, imgstadium, game, dateat")
    suspend fun getAllDetailCartForParent(): List<DetailCartParentEntity>
}