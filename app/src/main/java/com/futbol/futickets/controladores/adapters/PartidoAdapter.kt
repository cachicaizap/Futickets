package com.futbol.futickets.controladores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.futbol.futickets.R
import com.futbol.futickets.databinding.ItemListBinding
import com.futbol.futickets.entidades.Partido
import com.squareup.picasso.Picasso

class PartidoAdapter(val partidoItemList: List<Partido>) : RecyclerView.Adapter<PartidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return PartidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        val item = partidoItemList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return partidoItemList.size
    }


}

class PartidoViewHolder(partidoView: View) : RecyclerView.ViewHolder(partidoView){

    val binding = ItemListBinding.bind(partidoView)

    fun render(item: Partido){
        binding.txtEquipoUno.text = item.teamone
        binding.txtEquipoDos.text = item.teamtwo
        binding.textBoolean.text = item.avaible.toString()
        binding.txtFecha.text = item.dateat
        binding.txtHora.text = item.timeat
        binding.txtRonda.text = item.rounds

        Picasso.get().load(item.imgone).into(binding.imageLogoUno)
        Picasso.get().load(item.imgtwo).into(binding.imageLogoDos)
    }

}