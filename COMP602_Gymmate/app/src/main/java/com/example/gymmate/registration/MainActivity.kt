package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContinue = findViewById(R.id.btn_continue)

        // Button listener
        btnContinue.setOnClickListener {
            val intent = Intent(this@MainActivity, Name::class.java) // Move from MainActivity class to Name class
            startActivity(intent)
        }
    }
}
