package com.futbol.futickets.presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.futbol.futickets.R
import com.futbol.futickets.controladores.PartidoController
import com.futbol.futickets.databinding.ActivityItemBinding
import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.logica.PartidoBL
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

        var n: PartidoEntity? = null
        intent.extras?.let {
            n = Json.decodeFromString<PartidoEntity>(it.getString("partido").toString())
        }
        if(n != null){
            loadPartido(n!!)
        }

        binding.floatingActionButtonItem.setOnClickListener() {
            saveFavPartido(n)
        }
    }

    fun loadPartido(item: PartidoEntity){
        binding.txtEquipoUno.text = item.teamone
        binding.txtEquipoDos.text = item.teamtwo
        binding.txtFecha.text = item.dateat
        binding.txtRonda.text = item.rounds
        binding.txthora.text = item.timeat
        binding.txtEstadio.text = item.stadium
        Picasso.get().load(item.imgone).into(binding.imageLogoUno)
        Picasso.get().load(item.imgtwo).into(binding.imageLogoDos)
        Picasso.get().load(item.stadiumimg).into(binding.imageEstadio)

        lifecycleScope.launch(Dispatchers.Main) {
            fav = withContext(Dispatchers.IO) { PartidoBL().checkIsSaved(item.id) }
            if (fav) {
                binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
            }
        }
    }

    private fun saveFavPartido(partido: PartidoEntity?) {
        if(partido != null){
            if (!fav) {
                lifecycleScope.launch {
                    PartidoController().saveFavPartido(partido)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_24)
                }
            } else {
                lifecycleScope.launch {
                    PartidoController().deleteFavPartido(partido)
                    binding.floatingActionButtonItem.setImageResource(R.drawable.ic_favorite_border_12)
                }
            }
        }
    }
}