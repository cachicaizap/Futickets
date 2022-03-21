package com.futbol.futickets.controladores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.futbol.futickets.R
import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.data.database.entidades.DetailCartParentEntity
import com.futbol.futickets.databinding.ItemTicketsBinding
import com.squareup.picasso.Picasso

class DetailCartAdapter(val detCartItemList: List<DetailCartParentEntity>, val onClickItemSelected:(DetailCartParentEntity) -> Unit):
    RecyclerView.Adapter<DetailCartViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCartViewHolder{
        var layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_tickets, parent, false)
        return DetailCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailCartViewHolder, position: Int) {
        val item = detCartItemList[position]
        holder.render(item, onClickItemSelected)
    }

    override fun getItemCount(): Int {
        return detCartItemList.size
    }
}

class DetailCartViewHolder(detCartView: View) : RecyclerView.ViewHolder(detCartView){

    val binding = ItemTicketsBinding.bind(detCartView)

    fun render(item: DetailCartParentEntity, onClickItemSelected:(DetailCartParentEntity) -> Unit) {
        binding.textView20.text = item.game
        binding.textView21.text = item.quantity.toString()
        binding.textView24.text = item.cost.toString()
        binding.textView25.text = item.dateat

        Picasso.get().load(item.imgstadium).into(binding.imageLogoUno)

        binding.btnView.setOnClickListener{
            onClickItemSelected(item)
        }


    }
}