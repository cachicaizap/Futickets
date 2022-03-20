package com.futbol.futickets.logica

import com.futbol.futickets.casosUso.PartidoUseCase
import com.futbol.futickets.data.database.entidades.PartidoEntity

class PartidoBL {
    suspend fun getPartidosList(): List<PartidoEntity> {
        return PartidoUseCase().getAllPartidos()
    }

    suspend fun checkIsSaved(id: String): Boolean {
        val n = PartidoUseCase().getOnePartido(id)
        return n != null
    }

    suspend fun getFavoritesPartidos(): List<PartidoEntity> {
        return PartidoUseCase().getFavoritesPartidos()
    }

    suspend fun savePartidoFavPart(partido: PartidoEntity) {
        PartidoUseCase().savePartidoFavPart(partido)
    }

    suspend fun deletePartidoFavPart(partido: PartidoEntity) {
        PartidoUseCase().deletePartidoFavPart(partido)
    }
}