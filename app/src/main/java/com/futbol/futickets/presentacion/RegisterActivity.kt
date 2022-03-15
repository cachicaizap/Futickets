package com.futbol.futickets.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import android.view.View

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun goToLoginDisplay(view: View){
        val login = Intent(this, LoginActivity::class.java)
        startActivity(login)
        finish()
    }
}