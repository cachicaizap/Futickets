package com.futbol.futickets.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.futbol.futickets.controladores.adapters.DetailCartAdapter
import com.futbol.futickets.data.database.entidades.DetailCartParentEntity
import com.futbol.futickets.databinding.FragmentShopBinding
import com.futbol.futickets.logica.DetailCartBL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class ShopFragment : Fragment() {

    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarCart.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            val ltsTickets = withContext(Dispatchers.IO){
                DetailCartBL().getDetailCartsForParent()
            }
            loadTickets(ltsTickets)
        }
    }

    fun loadTickets(items: List<DetailCartParentEntity>){
        binding.listRecyclerViewCart.adapter =
            DetailCartAdapter(items) { getDetailCartItem(it) }
        binding.listRecyclerViewCart.layoutManager =
            LinearLayoutManager(binding.listRecyclerViewCart.context)
        binding.progressBarCart.visibility = View.INVISIBLE
    }

    fun getDetailCartItem(item: DetailCartParentEntity){
        var intent = Intent(activity, DetailsActivity::class.java)
        val jsonString = Json.encodeToString(item)
        intent.putExtra("ticket", jsonString)
        startActivity(intent)
    }
}