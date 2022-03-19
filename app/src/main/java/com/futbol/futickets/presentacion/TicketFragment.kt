package com.futbol.futickets.presentacion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.futbol.futickets.controladores.adapters.PartidoAdapter
import com.futbol.futickets.databinding.FragmentTicketBinding
import com.futbol.futickets.entidades.Partido
import com.futbol.futickets.logica.PartidoBL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.progressBar.visibility = View.VISIBLE


        GlobalScope.launch(Dispatchers.Main) {
            val ltsPartidos = withContext(Dispatchers.IO){
                PartidoBL().getPartidosList()
            }
            loadPartidos(ltsPartidos)
        }
    }

    fun loadPartidos(items: List<Partido>){
        binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)
        binding.listRecyclerView.adapter = PartidoAdapter(items) { getPartidoItem(it) }
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun getPartidoItem(item: Partido){
        var intent = Intent(binding.listRecyclerView.context, ItemActivity::class.java)
        val jsonString = Json.encodeToString(item)
        intent.putExtra("partido", jsonString)
        startActivity(intent)
    }
}