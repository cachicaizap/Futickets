package com.futbol.futickets.controladores

import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.logica.DetailCartBL
import com.futbol.futickets.logica.PartidoBL

class DetailCartController {
    suspend fun saveDetailCart(detail: DetailCartEntity) {
        DetailCartBL().saveDetailCart(detail)
    }

    suspend fun updateDetailCart(detail: DetailCartEntity) {
        DetailCartBL().updateDetailCart(detail)
    }

    suspend fun deleteFavPartido(detail: DetailCartEntity) {
        DetailCartBL().deleteDetailCart(detail)
    }
}