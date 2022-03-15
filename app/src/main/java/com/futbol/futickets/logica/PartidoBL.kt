package com.futbol.futickets.logica

import com.futbol.futickets.casosUso.PartidoUseCase
import com.futbol.futickets.entidades.Partido

class PartidoBL {
    fun getPartidosList(): List<Partido> {
        return PartidoUseCase().getAllPartidos()
    }
}