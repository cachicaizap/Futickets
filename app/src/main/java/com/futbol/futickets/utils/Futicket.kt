package com.futbol.futickets.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.futbol.futickets.data.database.TicketDataBase

class Futicket : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, TicketDataBase::class.java, "news_DB")
            .build()

        dbShare = applicationContext.getSharedPreferences("prefsData", Context.MODE_PRIVATE)

        dbPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    companion object {
        private var db: TicketDataBase? = null
        private lateinit var dbShare: SharedPreferences
        private lateinit var dbPreferences: SharedPreferences

        fun getDatabase(): TicketDataBase {
            return db!!
        }

        fun getShareDB(): SharedPreferences {
            return dbShare
        }

        fun getPrefseDB(): SharedPreferences {
            return dbPreferences
        }

    }
}