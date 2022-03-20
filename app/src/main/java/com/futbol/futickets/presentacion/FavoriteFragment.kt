package com.futbol.futickets.presentacion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.futbol.futickets.controladores.adapters.PartidoAdapter
import com.futbol.futickets.data.database.entidades.PartidoEntity
import com.futbol.futickets.databinding.FragmentFavoriteBinding
import com.futbol.futickets.logica.PartidoBL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.progressBarFav.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) {
                PartidoBL().getFavoritesPartidos()
            }
            binding.listRecyclerViewFav.adapter =
                PartidoAdapter(items) { getPartidoItem(it) }
            binding.listRecyclerViewFav.layoutManager =
                LinearLayoutManager(binding.listRecyclerViewFav.context)
            binding.progressBarFav.visibility = View.INVISIBLE
        }
    }

    fun getPartidoItem(item: PartidoEntity){
        var i = Intent(activity, ItemActivity::class.java)
        val jsonString = Json.encodeToString(item)
        i.putExtra("partido", jsonString)
        startActivity(i)
    }
}