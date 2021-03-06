package com.example.senku_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_main)
    }

    fun lanzaJugar(view: View){

        val i = Intent(this, Jugar::class.java)
        startActivity(i)

    }

    fun lanzaAyuda(view: View){

        val i = Intent(this, Ayuda::class.java)
        startActivity(i)

    }

    fun lanzaCreditos(view: View){

        val i = Intent(this, Creditos::class.java)
        startActivity(i)

    }
}


