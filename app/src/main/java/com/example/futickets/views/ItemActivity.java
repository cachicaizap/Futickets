package com.example.futickets.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.futickets.R;
import com.example.futickets.databinding.ItemListBinding;
import com.example.futickets.entities.Tickets;

public class ItemActivity extends AppCompatActivity{

    private ItemListBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ItemListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Tickets n = null;

        if (n != null){

        }
    }

    public void loadNews(Tickets tickets) {
        binding.txtTitleNews.setText(tickets.getEquipo1());
        binding.txtTitulo.text = news.title
        binding.txtAutor.text = news.author
        binding.txtDesc.text = news.desc
        Picasso.get().load(news.img).into(binding.imgNews)
    }
}
