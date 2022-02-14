package com.example.futickets.views;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import com.example.futickets.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Main extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new TicketsFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                    Fragment fragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.itTicket:
                            fragment = new TicketsFragment();
                            break;
                        case R.id.itFavorite:
                            fragment = new FavoritesFragment();
                            break;
                        case R.id.itShop:
                            fragment = new ShoppingFragment();
                            break;
                        case R.id.itSetting:
                            fragment = new SettingsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, fragment).commit();
                    return true;
                }
            };
}