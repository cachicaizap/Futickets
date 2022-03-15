package com.futbol.futickets.controladores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.futbol.futickets.data.database.entidades.TicketEntity
import com.squareup.picasso.Picasso
import com.futbol.futickets.R
import com.futbol.futickets.databinding.TicketsItemsBinding

class ListTicketsAdapter(val listTickets: List<TicketEntity>, val onClickItemSelected: (TicketEntity) -> Unit) :
    RecyclerView.Adapter<TicketsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TicketsViewHolder(inflater.inflate(R.layout.tickets_items, parent, false))
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val item = listTickets[position]
        holder.render(item, onClickItemSelected)
    }

    override fun getItemCount(): Int = listTickets.size
}

class TicketsViewHolder(itemTickets: View) : RecyclerView.ViewHolder(itemTickets) {

    private val binding: TicketsItemsBinding = TicketsItemsBinding.bind(itemTickets)

    fun render(itemTicketEntity: TicketEntity, onClickItemSelected: (TicketEntity) -> Unit) {
        binding.txtTitleNews.text = itemTicketEntity.team_one
        Picasso.get().load(itemTicketEntity.team_one_logo).into(binding.imageView)

        itemView.setOnClickListener {
            onClickItemSelected(itemTicketEntity)
        }
    }
}