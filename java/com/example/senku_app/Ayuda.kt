package com.example.senku_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class Ayuda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_ayuda)
    }
}
