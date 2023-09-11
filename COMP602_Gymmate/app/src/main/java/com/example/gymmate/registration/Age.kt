package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Age : AppCompatActivity() {

    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var etAge: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)

        btnPrevious = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)
        etAge = findViewById(R.id.et_age)

        // Button listeners
        btnPrevious.setOnClickListener {
            // Extract the entered age
            val enteredAge = etAge.text.toString()

            // Retrieve the name and gender from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")

            // Prepare to proceed to the Gender activity
            val intent = Intent(this@Age, Gender::class.java)

            // Pass the name, gender, and age information to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", enteredAge)

            // Initiate the transition to the next activity
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            // Extract the entered age
            val enteredAge = etAge.text.toString()

            // Retrieve the name and gender from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")

            // Prepare to proceed to the Height activity
            val intent = Intent(this@Age, Height::class.java)

            // Pass the name, gender, and age information to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", enteredAge)

            // Initiate the transition to the next activity
            startActivity(intent)
        }
    }
}
