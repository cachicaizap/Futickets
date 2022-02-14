package com.example.futickets.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.futickets.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFutickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashFutickets.this, Login.class);
                startActivity(intent);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 3000);
    }
}