package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Name : AppCompatActivity() {

    private lateinit var btnCancel: Button
    private lateinit var btnNext: Button
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        btnCancel = findViewById(R.id.btn_cancel)
        btnNext = findViewById(R.id.btn_next)
        etName = findViewById(R.id.et_name)

        // Cancel button listener
        btnCancel.setOnClickListener {
            val intent = Intent(this@Name, MainActivity::class.java)
            startActivity(intent)
        }

        // Next button listener
        btnNext.setOnClickListener {
            val name = etName.text.toString() // Get the entered name
            val intent = Intent(this@Name, Gender::class.java)
            intent.putExtra("name", name) // Pass the name to the next intent
            startActivity(intent)
        }
    }
}
