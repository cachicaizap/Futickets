package com.futbol.futickets.presentacion

import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.futbol.futickets.data.database.entidades.DetailCartParentEntity
import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.databinding.ActivityDetailsBinding
import com.futbol.futickets.logica.DetailCartBL
import com.futbol.futickets.logica.DetailTicketBL
import com.futbol.futickets.logica.PartidoBL
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var n: DetailCartParentEntity? = null
        intent.extras?.let {
            n = Json.decodeFromString<DetailCartParentEntity>(it.getString("ticket").toString())
        }
        if(n != null){
            loadDetailCart(n!!)
        }
    }

    fun loadDetailCart(item: DetailCartParentEntity){
        val str = item.game.split(" vs ")
        val servicio:Double = 2.65
        //Invisible
        visibleGeneralTicket(false)
        visibleTribunaTicket(false)
        visiblePalcoTicket(false)
        visiblePropTicket(false)
        //ver imagen
        binding.textView28.text = item.cost.toString()
        binding.textView31.text = servicio.toString()
        binding.txtEquipoUno.text = str[0]
        binding.txtEquipoDos.text = str[1]
        binding.txtFecha.text = item.dateat
        binding.textView32.text = (item.cost + servicio).toString()
        Picasso.get().load(item.imgstadium).into(binding.imageStadium)

        lifecycleScope.launch(Dispatchers.Main) {
            val ltsdetcart = withContext(Dispatchers.IO) {
                DetailCartBL().getDetailCartIdParent(item.idparent)
            }
            var hour = ""
            ltsdetcart.forEach{
                when {
                    it.location == "General" -> {
                        val ver = it.quantity > 0
                        visibleGeneralTicket(ver)
                        binding.txtQuanGen.text = it.quantity.toString()
                        binding.textView15.text = it.cost.toString()
                    }
                    it.location == "Tribuna" -> {
                        val ver = it.quantity > 0
                        visibleTribunaTicket(ver)
                        binding.txtQuanTrib.text = it.quantity.toString()
                        binding.textView16.text = it.cost.toString()
                    }
                    it.location == "Palco" -> {
                        val ver = it.quantity > 0
                        visiblePalcoTicket(ver)
                        binding.txtQuanPal.text = it.quantity.toString()
                        binding.textView18.text = it.cost.toString()
                    }
                    it.location == "Propietario" -> {
                        val ver = it.quantity > 0
                        visiblePropTicket(ver)
                        binding.txtQuanPro.text = it.quantity.toString()
                        binding.txtCostProp.text = it.cost.toString()
                    }
                }
                hour = it.timeat
            }
            binding.txtHora.text = hour
        }
    }

    private fun visibleGeneralTicket(ver:Boolean){
        if(!ver) {
            binding.cardView4.visibility = View.INVISIBLE
            binding.textViewGen.visibility = View.INVISIBLE
            binding.textView14.visibility = View.INVISIBLE
            binding.textView15.visibility = View.INVISIBLE
        }else{
            binding.cardView4.visibility = View.VISIBLE
            binding.textViewGen.visibility = View.VISIBLE
            binding.textView14.visibility = View.VISIBLE
            binding.textView15.visibility = View.VISIBLE
        }
    }

    private fun visibleTribunaTicket(ver:Boolean){
        if(!ver) {
            binding.cardView5.visibility = View.INVISIBLE
            binding.textViewTrib.visibility = View.INVISIBLE
            binding.textView17.visibility = View.INVISIBLE
            binding.textView16.visibility = View.INVISIBLE
        }else{
            binding.cardView5.visibility = View.VISIBLE
            binding.textViewTrib.visibility = View.VISIBLE
            binding.textView17.visibility = View.VISIBLE
            binding.textView16.visibility = View.VISIBLE
        }
    }

    private fun visiblePalcoTicket(ver:Boolean){
        if(!ver){
            binding.cardView6.visibility = View.INVISIBLE
            binding.textViewPalco.visibility = View.INVISIBLE
            binding.textView19.visibility = View.INVISIBLE
            binding.textView18.visibility = View.INVISIBLE
        }else{
            binding.cardView6.visibility = View.VISIBLE
            binding.textViewPalco.visibility = View.VISIBLE
            binding.textView19.visibility = View.VISIBLE
            binding.textView18.visibility = View.VISIBLE
        }
    }

    private fun visiblePropTicket(ver:Boolean){
        if(!ver) {
            binding.cardView7.visibility = View.INVISIBLE
            binding.textViewProp.visibility = View.INVISIBLE
            binding.txtsignProp.visibility = View.INVISIBLE
            binding.txtCostProp.visibility = View.INVISIBLE
        }else{
            binding.cardView7.visibility = View.VISIBLE
            binding.textViewProp.visibility = View.VISIBLE
            binding.txtsignProp.visibility = View.VISIBLE
            binding.txtCostProp.visibility = View.VISIBLE
        }
    }
}