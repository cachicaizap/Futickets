package com.futbol.futickets.presentacion

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


class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)

        loadPartidos(PartidoBL().getPartidosList())

        return binding.root
    }

    fun loadPartidos(items: List<Partido>){
        binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)
        binding.listRecyclerView.adapter = PartidoAdapter(items) { getPartidoItem(it) }
    }

    fun getPartidoItem(item: Partido){
        Toast.makeText(binding.listParentLayout.context,"El item es: "+item.id,Toast.LENGTH_SHORT).show()
    }
}