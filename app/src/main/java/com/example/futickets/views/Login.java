package com.example.futickets.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futickets.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToRegisterDisplay(View view){
        Intent register = new Intent(this, Register.class);
        startActivity(register);
        finish();
    }

    public void goToMainDisplay(View view){
        Intent main = new Intent(this, Main.class);
        startActivity(main);
        finish();
    }
}