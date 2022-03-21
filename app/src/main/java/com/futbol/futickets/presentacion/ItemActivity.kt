package com.futbol.futickets.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.futbol.futickets.R
import com.futbol.futickets.controladores.DetailCartController
import com.futbol.futickets.controladores.PartidoController
import com.futbol.futickets.data.database.entidades.DetailCartEntity
import com.futbol.futickets.data.database.entidades.DetailTicketEntity
import com.futbol.futickets.databinding.ActivityItemBinding
import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.logica.DetailCartBL
import com.futbol.futickets.logica.DetailTicketBL
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
    private var detail: List<DetailTicketEntity> = ArrayList<DetailTicketEntity>()

    //detalles del carrito
    private var detcartgen: DetailCartEntity? = null
    private var detcartrib: DetailCartEntity? = null
    private var detcartpal: DetailCartEntity? = null
    private var detcartpro: DetailCartEntity? = null

    //numero de tickets
    private var ngeneral: Int = 0
    private var ntribuna: Int = 0
    private var npalco: Int = 0
    private var npropietario = 0

    //numero de tickets disp
    private var ndispgeneral: Int = 0
    private var ndisptribuna: Int = 0
    private var ndisppalco: Int = 0
    private var ndisppropietario = 0

    //costo por ticket
    private var costgeneral:Double = 0.0
    private var costtribuna:Double = 0.0
    private var costpalco:Double = 0.0
    private var costpropiet:Double = 0.0

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

        binding.floatingCart.setOnClickListener(){
            saveAñadirCart(n, detcartgen, detcartrib, detcartpal, detcartpro)
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

        binding.floatingCart.isEnabled = false

        //Comprobar si esta o no check
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                fav = PartidoBL().checkIsSaved(item.id)
                detail = DetailTicketBL().getDetailFromPartido(item.id)
                detcartgen = DetailCartBL().getOneDetailCart(item.id+"n1")
                detcartrib = DetailCartBL().getOneDetailCart(item.id+"n2")
                detcartpal = DetailCartBL().getOneDetailCart(item.id+"n3")
                detcartpro = DetailCartBL().getOneDetailCart(item.id+"n4")
            }

            if(detail != null){
                //Locación general
                costgeneral = detail[0].cost
                ndispgeneral = detail[0].quantity
                //locación tribuna
                costtribuna = detail[1].cost
                ndisptribuna = detail[1].quantity
                //locación palco
                costpalco = detail[2].cost
                ndisppalco = detail[2].quantity
                //locación propiestario
                costpropiet = detail[3].cost
                ndisppropietario = detail[3].quantity
            }

            if(detcartgen!=null){
                ngeneral = detcartgen!!.quantity
            }

            if(detcartrib!=null){
                ntribuna = detcartrib!!.quantity
            }

            if(detcartpal!=null){
                npalco = detcartpal!!.quantity
            }

            if(detcartpro!=null){
                npropietario = detcartpro!!.quantity
            }

            calcGeneral()
            calcTribuna()
            calcPalco()
            calcPropietario()

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

    private fun saveAñadirCart(item: PartidoEntity?, detgen: DetailCartEntity?, detrib: DetailCartEntity?, detpalco: DetailCartEntity?, detprop: DetailCartEntity?){
        if(item != null){
            if(detgen!=null){
                detgen.quantity = ngeneral
                detgen.cost = (ngeneral * costgeneral)
                lifecycleScope.launch {
                    DetailCartController().updateDetailCart(detgen)
                }
            }else{
                val detailcart = DetailCartEntity(
                    item.id+"n1", item.stadiumimg+"",item.teamone+" vs "+item.teamtwo,
                    item.dateat+"",item.timeat+"",
                    "General",ngeneral * costgeneral, ngeneral, item.id
                )
                lifecycleScope.launch {
                    DetailCartController().saveDetailCart(detailcart)
                }
                print("Entro general")
            }
            if(detrib!=null){
                detrib.quantity = ntribuna
                detrib.cost = (ntribuna * costtribuna)
                lifecycleScope.launch {
                    DetailCartController().updateDetailCart(detrib)
                }
            }else{
                val detailcart = DetailCartEntity(
                    item.id+"n2",item.stadiumimg+"",item.teamone+" vs "+item.teamtwo,
                    item.dateat+"",item.timeat+"",
                    "Tribuna",ntribuna * costtribuna, ntribuna, item.id
                )
                lifecycleScope.launch {
                    DetailCartController().saveDetailCart(detailcart)
                }
                print("Entro tribunal")
            }
            if(detpalco!=null){
                detpalco.quantity = npalco
                detpalco.cost = (npalco * costpalco)
                lifecycleScope.launch {
                    DetailCartController().updateDetailCart(detpalco)
                }
            }else{
                val detailcart = DetailCartEntity(
                    item.id+"n3", item.stadiumimg+"",item.teamone+" vs "+item.teamtwo,
                    item.dateat+"", item.timeat+"",
                    "Palco", npalco * costpalco, npalco, item.id
                )
                lifecycleScope.launch {
                    DetailCartController().saveDetailCart(detailcart)
                }
                print("Entro palco")
            }
            if(detprop!=null){
                detprop.quantity = npropietario
                detprop.cost = (npropietario * costpropiet)
                lifecycleScope.launch {
                    DetailCartController().updateDetailCart(detprop)
                }
            }else{
                val detailcart = DetailCartEntity(
                    item.id+"n4", item.stadiumimg+"",item.teamone+" vs "+item.teamtwo,
                    item.dateat+"", item.timeat+"",
                    "Propietario", npropietario * costpropiet, npropietario,item.id
                )
                lifecycleScope.launch {
                    DetailCartController().saveDetailCart(detailcart)
                }
                print("Entro propietario")
            }
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun calcGeneral(){
        binding.textView2.text = ngeneral.toString()
        binding.textView7.text = if( (ngeneral * costgeneral) == 0.0){ costgeneral.toString() }else{ (ngeneral * costgeneral).toString() }
        binding.txtDispGeneral.text = (ndispgeneral - ngeneral).toString()
        val suma = ngeneral + ntribuna + npalco + npropietario
        binding.floatingCart.text = if (suma == 0){ "Añadir al carrito" }else{ "Añadir ("+suma+") al carrito" }
        binding.floatingCart.isEnabled = suma != 0
    }

    fun calcTribuna(){
        binding.textView4.text = ntribuna.toString()
        binding.textView9.text = if( (ntribuna * costtribuna) == 0.0){ costtribuna.toString() }else{ (ntribuna * costtribuna).toString() }
        binding.txtDispTribuna.text = (ndisptribuna - ntribuna).toString()
        val suma = ngeneral + ntribuna + npalco + npropietario
        binding.floatingCart.text = if (suma == 0){ "Añadir al carrito" }else{ "Añadir ("+suma+") al carrito" }
        binding.floatingCart.isEnabled = suma != 0
    }

    fun calcPalco(){
        binding.textView6.text = npalco.toString()
        binding.textView10.text = if( (npalco * costpalco) == 0.0){ costpalco.toString() }else{ (npalco * costpalco).toString() }
        binding.txtDispPalco.text = (ndisppalco - npalco).toString()
        val suma = ngeneral + ntribuna + npalco + npropietario
        binding.floatingCart.text = if (suma == 0){ "Añadir al carrito" }else{ "Añadir ("+suma+") al carrito" }
        binding.floatingCart.isEnabled = suma != 0
    }

    fun calcPropietario(){
        binding.textVie1.text = npropietario.toString()
        binding.textView11.text = if( (npropietario * costpropiet) == 0.0){ costpropiet.toString() }else{ (npropietario * costpropiet).toString() }
        binding.txtDispPropietario.text = (ndisppropietario - npropietario).toString()
        val suma = ngeneral + ntribuna + npalco + npropietario
        binding.floatingCart.text = if (suma == 0){ "Añadir al carrito" }else{ "Añadir ("+suma+") al carrito" }
        binding.floatingCart.isEnabled = suma != 0
    }

    fun clickPlusGeneral(view: View){
        if(ngeneral<4){ ngeneral++ }
        calcGeneral()
    }

    fun clickMinusGeneral(view: View){
        if(ngeneral>0){ ngeneral-- }
        calcGeneral()
    }

    fun clickPlusTribuna(view: View){
        if(ntribuna<4){ ntribuna++ }
        calcTribuna()
    }

    fun clickMinusTribuna(view: View){
        if(ntribuna>0){ ntribuna-- }
        calcTribuna()
    }

    fun clickPlusPalco(view: View){
        if(npalco<4){ npalco++ }
        calcPalco()
    }

    fun clickMinusPalco(view: View){
        if(npalco>0){ npalco-- }
        calcPalco()
    }

    fun clickPlusPropietario(view: View){
        if(npropietario<4){ npropietario++ }
        calcPropietario()
    }

    fun clickMinusPropietario(view: View){
        if(npropietario>0){ npropietario-- }
        calcPropietario()
    }
}