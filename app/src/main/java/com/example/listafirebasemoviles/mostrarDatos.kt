package com.example.listafirebasemoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mostrar_datos.*

enum class ProviderType{
    BASIC
}

class mostrarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_datos)

        //
        val bunble = intent.extras
        val email = bunble?.getString("email")
        val provider = bunble?.getString("provider")
        setup(email ?: "", provider ?: "" )


        ircamara.setOnClickListener {
            val intent:Intent = Intent(this, camara::class.java)
            startActivity(intent)
        }
    }

    fun setup(email: String, provider: String){

        title = "Inicio"
        emailView.text = email
        tipoUs.text = provider

        cerraBoton.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }




}


