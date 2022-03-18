package com.futbol.futickets.casosUso

import com.futbol.futickets.entidades.Partido
import java.sql.Time
import java.util.*

class PartidoUseCase {

    private val partidoslist = listOf<Partido>(
        Partido(
            "Aucas",
            "https://ssl.gstatic.com/onebox/media/sports/logos/aZvLUY_82UUB0czzaA7c8g_48x48.png",
            "Delfín",
            "https://ssl.gstatic.com/onebox/media/sports/logos/7k2Pzhqaiqe7GBGqb06t3A_48x48.png",
            "Primera ronda",
            false,
            "2022-02-26",
            "15:30",
            "Chillogallo"
        ),
        Partido(
            "U. Católica",
            "https://ssl.gstatic.com/onebox/media/sports/logos/xR8t4XRhh-fUtJuT3QrCVA_48x48.png",
            "Guayaquil City",
            "https://ssl.gstatic.com/onebox/media/sports/logos/rPA25xoGMWl1tQ1e9US_Kw_48x48.png",
            "Primera ronda",
            false,
            "2022-02-26",
            "17:30",
            "Olimpico Atahualpa"
        ),
        Partido(
            "Aucas",
            "https://ssl.gstatic.com/onebox/media/sports/logos/aZvLUY_82UUB0czzaA7c8g_48x48.png",
            "Delfín",
            "https://ssl.gstatic.com/onebox/media/sports/logos/7k2Pzhqaiqe7GBGqb06t3A_48x48.png",
            "Primera ronda",
            false,
            "2022-02-26",
            "15:30",
            "Chillogallo"
        ),
        Partido(
            "U. Católica",
            "https://ssl.gstatic.com/onebox/media/sports/logos/xR8t4XRhh-fUtJuT3QrCVA_48x48.png",
            "Guayaquil City",
            "https://ssl.gstatic.com/onebox/media/sports/logos/rPA25xoGMWl1tQ1e9US_Kw_48x48.png",
            "Primera ronda",
            false,
            "2022-02-26",
            "17:30",
            "Olimpico Atahualpa"
        )
    )

    fun getAllPartidos(): List<Partido>{
        return partidoslist
    }
}