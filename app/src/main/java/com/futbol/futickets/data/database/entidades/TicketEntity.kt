package com.futbol.futickets.data.database.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "tickets")
@Serializable
data class TicketEntity(
    @PrimaryKey
    val id: String,
    val team_one: String?,
    var team_one_logo: String?,
    val team_two: String?,
    var team_two_logo: String?,
    val price: Float?,
    val quantity: Int?,
    val rounds: String?,
    val avaible: Boolean=true,
    val date_at: String?,
    val time_at: String?,
    val stadium: String?
){
    init {
        if (this.team_one_logo == null) {
            this.team_one_logo = "https://ssl.gstatic.com/onebox/media/sports/logos/Iuk3Emwfmii37cXTu4qJEQ_96x96.png"
        }
        if (this.team_two_logo == null) {
            this.team_two_logo = "https://ssl.gstatic.com/onebox/media/sports/logos/snzD6Ep_orwAcAA5c5sPNw_96x96.png"
        }
    }
}
