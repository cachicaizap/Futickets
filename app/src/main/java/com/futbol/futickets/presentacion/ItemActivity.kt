package com.futbol.futickets.presentacion

import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import com.futbol.futickets.databinding.ActivityItemBinding
import com.futbol.futickets.entidades.Partido
import com.squareup.picasso.Picasso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var n: Partido? = null
        intent.extras?.let {
            n = Json.decodeFromString<Partido>(it.getString("partido").toString())
        }
        if(n != null){
            loadPartido(n!!)
        }
    }

    fun loadPartido(item: Partido){
        binding.txtEquipoUno.text = item.teamone
        binding.txtEquipoDos.text = item.teamtwo
        binding.txtFecha.text = item.dateat
        binding.txtRonda.text = item.rounds
        binding.txthora.text = item.timeat
        binding.txtEstadio.text = item.stadium
        Picasso.get().load(item.imgone).into(binding.imageLogoUno)
        Picasso.get().load(item.imgtwo).into(binding.imageLogoDos)
        Picasso.get().load(item.stadiumimg).into(binding.imageEstadio)
    }
}