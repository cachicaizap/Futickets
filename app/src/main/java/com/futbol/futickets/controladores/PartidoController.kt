package com.futbol.futickets.controladores

import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.logica.PartidoBL

class PartidoController {
    suspend fun getAllPartidos(): List<PartidoEntity> {
        return PartidoBL().getPartidosList()
    }

    suspend fun saveFavPartido(partido: PartidoEntity) {
        PartidoBL().savePartidoFavPart(partido)
    }

    suspend fun deleteFavPartido(partido: PartidoEntity) {
        PartidoBL().deletePartidoFavPart(partido)
    }
}