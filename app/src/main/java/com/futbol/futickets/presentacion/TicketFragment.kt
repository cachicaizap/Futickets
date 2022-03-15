package com.futbol.futickets.presentacion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.futbol.futickets.R
import com.futbol.futickets.controladores.TicketController
import com.futbol.futickets.controladores.adapters.ListTicketsAdapter
import com.futbol.futickets.data.database.entidades.TicketEntity
import com.futbol.futickets.databinding.FragmentTicketBinding
import com.futbol.futickets.utils.Futicket
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.lifecycle.Observer
import androidx.core.view.isVisible


class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding
    private val ticketViewModel: TicketController by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loadTickets()

        binding.listRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    loadTickets()
                }
            }
        })

        binding.swipeRefresh.setOnRefreshListener {
            loadTickets()
            binding.swipeRefresh.isRefreshing = false
        }

        ticketViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = it
        })


        ticketViewModel.retNews.observe(viewLifecycleOwner, Observer {
            binding.listRecyclerView.layoutManager =
                LinearLayoutManager(binding.listRecyclerView.context)
            binding.listRecyclerView.adapter = ListTicketsAdapter(it) { getTicketsItem(it) }
        })
    }

    fun loadTickets() {
        binding.listRecyclerView.clearAnimation()
        val db = Futicket.getPrefseDB()
        var keys = ArrayList<Int>()
        val apis = listOf<Int>(
            R.string.ticketsapi
        )
        apis.forEach {
            val check = resources.getResourceEntryName(it)
            if (db.contains(check)) {
                keys.add(it)
            }
        }

        if (keys.isEmpty()) {
            Toast.makeText(
                binding.listParentLayout.context,
                "No hay fuentes de información seleccionadas",
                Toast.LENGTH_SHORT
            ).show()
            clear()
        } else {
            ticketViewModel.getNews(keys)
        }
    }

    private fun getTicketsItem(ticketEntity: TicketEntity) {
        var i = Intent(activity, ItemActivity::class.java)
        val jsonString = Json.encodeToString(ticketEntity)
        i.putExtra("ticket", jsonString)
        startActivity(i)
    }

    fun clear() {
        binding.listRecyclerView.adapter = ListTicketsAdapter(emptyList()) { }
    }
}