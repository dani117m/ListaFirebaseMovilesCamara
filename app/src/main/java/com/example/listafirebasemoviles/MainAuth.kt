package com.example.listafirebasemoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import kotlin.math.sign
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_mostrar_datos.*
import org.w3c.dom.Text



class MainAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)



        ///
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("massage", "Integracion de FireBase")
        analytics.logEvent("InitScreen",Bundle())


        //
        setup()
    }

    private fun setup(){


        title = "Autenticacion"

        regisboton.setOnClickListener{
            if(editemail.text.isNotEmpty() && contraEdit.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editemail.text.toString(),
                contraEdit.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                    }else{
                        showAlert()

                    }
                }

            }
        }
            //isSuccessful
        acederboton.setOnClickListener{
            if(editemail.text.isNotEmpty() && contraEdit.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(editemail.text.toString(),
                    contraEdit.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                    }else{
                        showAlert()

                    }
                }

            }
        }



    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de registro ")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()


    }

    private fun showHome(email:String, provider: ProviderType){
        val homeIntent:Intent=Intent(this,mostrarDatos::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}