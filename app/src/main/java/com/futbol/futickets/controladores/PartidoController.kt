package com.futbol.futickets.controladores

import com.futbol.futickets.entidades.Partido
import com.futbol.futickets.logica.PartidoBL

class PartidoController {
    fun getAllPartidos(): List<Partido> {
        return PartidoBL().getPartidosList()
    }
}