package com.example.senku_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Window
import kotlinx.android.synthetic.main.activity_ayuda.*

class Ayuda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        setContentView(R.layout.activity_ayuda)

        instruccionesUno.movementMethod = ScrollingMovementMethod.getInstance()
    }
}
