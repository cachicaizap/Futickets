package com.futbol.futickets.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun goToRegisterDisplay(view:View){
        val register = Intent(this, RegisterActivity::class.java)
        startActivity(register)
        finish()
    }

    fun goToMainDisplay(view:View){
        val main = Intent(this, MainActivity::class.java)
        startActivity(main)
        finish();
    }
}