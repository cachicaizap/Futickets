package com.futbol.futickets.casosUso

import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.data.database.entidades.DetailCartParentEntity
import com.futbol.futickets.utils.Futicket

class DetailCartUseCase {
    suspend fun getDetailCarts(): List<DetailCartEntity> {
        val db = Futicket.getDatabase()
        return db.detailCartDAO().getAllDetailCart()
    }

    suspend fun getDetailCartsForParent(): List<DetailCartParentEntity> {
        return Futicket.getDatabase().detailCartDAO().getAllDetailCartForParent()
    }

    suspend fun saveDetailCart(detail: DetailCartEntity) {
        Futicket.getDatabase().detailCartDAO().insertDetailCart(detail)
    }

    suspend fun deleteDetailCart(detail: DetailCartEntity) {
        Futicket.getDatabase().detailCartDAO().deleteDetailCartById(detail.id!!)
    }

    suspend fun getOneDetailCart(id: String): DetailCartEntity {
        return Futicket.getDatabase().detailCartDAO().getDetailCartById(id)
    }

    suspend fun getOneDetailCartByParent(id: String): List<DetailCartEntity> {
        return Futicket.getDatabase().detailCartDAO().getDetailCartByIdParent(id)
    }

    suspend fun updateDetailCart(detail: DetailCartEntity) {
        Futicket.getDatabase().detailCartDAO().updateDetailCart(detail)
    }
}