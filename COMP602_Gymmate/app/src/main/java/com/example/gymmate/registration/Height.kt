package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Height : AppCompatActivity() {

    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var etHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        btnPrevious = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)
        etHeight = findViewById(R.id.et_height)

        // Button listeners
        btnPrevious.setOnClickListener {
            // Extract the entered height
            val enteredHeight = etHeight.text.toString()

            // Retrieve the name, gender, and age from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")

            // Prepare to proceed to the Age activity
            val intent = Intent(this@Height, Age::class.java)

            // Pass the name, gender, age, and height information to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", enteredHeight)

            // Initiate the transition to the next activity
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            // Extract the entered height
            val enteredHeight = etHeight.text.toString()

            // Retrieve the name, gender, and age from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")

            // Prepare to proceed to the Weight activity
            val intent = Intent(this@Height, Weight::class.java)

            // Pass the name, gender, age, and height information to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", enteredHeight)

            // Initiate the transition to the next activity
            startActivity(intent)
        }
    }
}
