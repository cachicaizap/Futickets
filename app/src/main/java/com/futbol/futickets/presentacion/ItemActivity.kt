package com.futbol.futickets.presentacion

import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.futbol.futickets.controladores.TicketController
import com.futbol.futickets.data.database.entidades.TicketEntity
import com.futbol.futickets.databinding.ActivityItemBinding
import com.futbol.futickets.logica.TicketBL
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    private var fav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var n: TicketEntity? = null
        intent.extras?.let {
            n = Json.decodeFromString<TicketEntity>(it.getString("ticket").toString())
        }
        if (n != null) {
            loadNews(n!!)
        }

        binding.floatingActionButtonItem.setOnClickListener() {
            saveFavNews(n)
        }
    }

    private fun loadNews(ticketEntity: TicketEntity) {
        binding.txtTitulo.text = ticketEntity.team_one
        binding.txtAutor.text = ticketEntity.stadium
        binding.txtDesc.text = ticketEntity.rounds
        Picasso.get().load(ticketEntity.team_one_logo).into(binding.imgNews)

        lifecycleScope.launch(Dispatchers.Main) {
            fav = withContext(Dispatchers.IO) { TicketBL().checkIsSaved(ticketEntity.id) }
            if (fav) {
                binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
            }
        }
    }

    private fun saveFavNews(tickets: TicketEntity?) {
        if (tickets != null) {
            if (!fav) {
                lifecycleScope.launch {
                    TicketController().saveFavTicket(tickets)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
                }
            } else {
                lifecycleScope.launch {
                    TicketController().deleteFavTicket(tickets)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_border_12)
                }
            }
        }
    }
}