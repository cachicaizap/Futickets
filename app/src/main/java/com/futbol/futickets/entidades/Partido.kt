package com.futbol.futickets.entidades

import java.sql.Time
import java.util.*

data class Partido(var id:String = "-1L", var teamone:String = "",
                   var imgone:String = "", var teamtwo:String = "",
                   var imgtwo:String = "", var rounds:String = "",
                   var avaible:Boolean = false, var dateat: String = "",
                   var timeat:String = "", var stadium:String = "") {
    constructor(teamone: String, imgone: String,
                teamtwo: String, imgtwo: String,
                rounds: String, avaible: Boolean,
                dateat: String, timeat: String,
                stadium: String): this(){

        this.teamone = teamone
        this.imgone = imgone
        this.teamtwo = teamtwo
        this.imgtwo = imgtwo
        this.rounds = rounds
        this.avaible = avaible
        this.dateat = dateat
        this.timeat = timeat
        this.stadium = stadium
        this.id = UUID.randomUUID().toString()
    }
}
