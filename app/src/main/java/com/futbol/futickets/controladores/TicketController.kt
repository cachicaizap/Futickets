package com.futbol.futickets.controladores

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futbol.futickets.R
import com.futbol.futickets.data.database.entidades.TicketEntity
import com.futbol.futickets.logica.TicketBL
import kotlinx.coroutines.launch

class TicketController : ViewModel() {

    val retNews = MutableLiveData<List<TicketEntity>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getNews(apiType: List<Int>) {
        viewModelScope.launch {
            isLoading.postValue(true)
            var ret: ArrayList<TicketEntity> = ArrayList<TicketEntity>()
            apiType.forEach {
                Log.d("TAG", it.toString())
                when (it) {
                    R.string.ticketsapi -> {
                        ret.addAll(TicketBL().getTicketsList())
                    }
                    else -> {
                        listOf<TicketEntity>()
                    }
                }
            }
            Log.d("TAG", "Tamaño:  ${ret.size}")
            retNews.postValue(ret)
            isLoading.postValue(false)
        }
    }

    suspend fun saveFavTicket(tickets: TicketEntity) {
        TicketBL().saveTicketFavourite(tickets)
    }

    suspend fun deleteFavTicket(tickets: TicketEntity) {
        TicketBL().deleteTicketFavourite(tickets)
    }
}