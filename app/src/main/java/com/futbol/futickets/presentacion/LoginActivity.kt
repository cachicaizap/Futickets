package com.futbol.futickets.presentacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import android.view.View
import com.futbol.futickets.controladores.UsuarioController
import com.futbol.futickets.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun goToRegisterDisplay(view:View){
        val register = Intent(this, RegisterActivity::class.java)
        startActivity(register)
        finish()
    }

    fun goToMainDisplay(view:View){
        val access = UsuarioController().LoginUser(
            binding.txtEmail.text.toString(),
            binding.txtPassword.text.toString()
        )
        if (access) {
            binding.emailField.error = "username o email incorrectos"
        }else{
            binding.emailField.error = null
            val main = Intent(this, MainActivity::class.java)
            startActivity(main)
            finish()
        }
    }
}