package com.futbol.futickets.logica

import com.futbol.futickets.casosUso.DetailCartUseCase
import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.data.database.entidades.DetailCartParentEntity

class DetailCartBL {
    suspend fun getOneDetailCart(id: String): DetailCartEntity {
        return DetailCartUseCase().getOneDetailCart(id)
    }

    suspend fun getDetailCartIdParent(id: String): List<DetailCartEntity> {
        return DetailCartUseCase().getOneDetailCartByParent(id)
    }

    suspend fun getDetailCarts(): List<DetailCartEntity> {
        return DetailCartUseCase().getDetailCarts()
    }

    suspend fun getDetailCartsForParent(): List<DetailCartParentEntity> {
        return DetailCartUseCase().getDetailCartsForParent()
    }

    suspend fun saveDetailCart(detail: DetailCartEntity) {
        DetailCartUseCase().saveDetailCart(detail)
    }

    suspend fun deleteDetailCart(detail: DetailCartEntity) {
        DetailCartUseCase().deleteDetailCart(detail)
    }

    suspend fun updateDetailCart(detail: DetailCartEntity) {
        DetailCartUseCase().updateDetailCart(detail)
    }
}