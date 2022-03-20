package com.futbol.futickets.utils

import android.app.Application
import androidx.room.Room
import com.futbol.futickets.data.database.FutbolDatabase

class Futicket: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, FutbolDatabase::class.java, "futbol_db")
            .build()
    }

    companion object {
        private var db: FutbolDatabase? = null

        fun getDatabase(): FutbolDatabase {
            return db!!
        }
    }
}