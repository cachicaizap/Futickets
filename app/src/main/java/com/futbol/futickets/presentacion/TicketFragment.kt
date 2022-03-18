package com.futbol.futickets.presentacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.futbol.futickets.controladores.adapters.PartidoAdapter
import com.futbol.futickets.databinding.FragmentTicketBinding
import com.futbol.futickets.logica.PartidoBL


class TicketFragment : Fragment() {

    private lateinit var binding: FragmentTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)

        val lstPartido = PartidoBL().getPartidosList()
        binding.listRecyclerView.adapter = PartidoAdapter(lstPartido)
        binding.listRecyclerView.layoutManager = LinearLayoutManager(binding.listRecyclerView.context)

        return binding.root
    }
}