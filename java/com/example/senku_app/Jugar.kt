package com.example.senku_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window

class Jugar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_jugar)
    }

    fun tableroCruz(view: View){


        resetValores()

        val i = Intent(this, TableroCruz::class.java)
        startActivity(i)

        play(this, R.raw.start)
    }

    fun tableroPiramide(view: View){

        resetValores()

        val i = Intent(this, TableroPiramide::class.java)
        startActivity(i)

        play(this, R.raw.start)
    }
}
