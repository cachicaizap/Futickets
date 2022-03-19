package com.futbol.futickets.casosUso

import com.futbol.futickets.entidades.Partido
import java.sql.Time
import java.util.*

class PartidoUseCase {

    private val partidoslist = listOf<Partido>(
        Partido(
            "Aucas",
            "https://suramericafc.com/Files/Futbolistas%20destacados/579ec84fa97a480c8e25af3bf0958137/ecuaucas190x227.png",
            "Delfín",
            "https://ldu.com.ec/web/wp-content/uploads/2015/06/Delfin_SC_Logo.png",
            "Primera ronda",
            false,
            "2022-02-26",
            "15:30",
            "Chillogallo",
            "https://p7s5h3q6.stackpathcdn.com/wp-content/uploads/2021/04/CZIVPU_WQAA0bgB.jpg"
        ),
        Partido(
            "U. Católica",
            "https://ssl.gstatic.com/onebox/media/sports/logos/xR8t4XRhh-fUtJuT3QrCVA_96x96.png",
            "Guayaquil City",
            "https://2.bp.blogspot.com/-FbRcIvqMwV4/XDvchd4WeAI/AAAAAAABTvc/m2UBvGcEYJkmR5oUIKLJgaZlRsq8MddCQCLcBGAs/s1600/Guayaquil%2BCity%2BFC.png",
            "Primera ronda",
            false,
            "2022-02-26",
            "17:30",
            "Olimpico Atahualpa",
            "https://imagenes.expreso.ec/files/image_700_402/uploads/2020/01/14/5e1deb5f3b90f.jpeg"
        )
    )

    fun getAllPartidos(): List<Partido>{
        return partidoslist
    }
}