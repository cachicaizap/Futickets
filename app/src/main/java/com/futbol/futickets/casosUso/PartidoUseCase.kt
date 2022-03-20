package com.futbol.futickets.casosUso

import com.futbol.futickets.data.api.FutRetrofitApi
import com.futbol.futickets.data.api.entidades.toPartidoEntity
import com.futbol.futickets.data.api.service.PartidoService
import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.utils.Futicket

class PartidoUseCase {

    private val partidoslist = listOf<PartidoEntity>(
        PartidoEntity(
            "1",
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
        PartidoEntity(
            "2",
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

    suspend fun getAllPartidos(): List<PartidoEntity>{
        var resp: MutableList<PartidoEntity> = ArrayList<PartidoEntity>()
        val service = FutRetrofitApi.getFutApi().create(PartidoService::class.java)
        val call = service.fetchGames()
        resp = if(call.isSuccessful){
            val body = call.body()
            body!!.map {
                it.toPartidoEntity()
            } as MutableList<PartidoEntity>
        }else{
            ArrayList<PartidoEntity>()
        }
        return resp
    }

    suspend fun getFavoritesPartidos(): List<PartidoEntity> {
        val db = Futicket.getDatabase()
        return db.partidoDao().getAllPartidos()
    }

    suspend fun savePartidoFavPart(partido: PartidoEntity) {
        Futicket.getDatabase().partidoDao().insertPartido(partido)
    }

    suspend fun deletePartidoFavPart(partido: PartidoEntity) {
        Futicket.getDatabase().partidoDao().deletePartidoById(partido.id)
    }

    suspend fun getOnePartido(id: String): PartidoEntity {
        return Futicket.getDatabase().partidoDao().getPartidoById(id)
    }
}