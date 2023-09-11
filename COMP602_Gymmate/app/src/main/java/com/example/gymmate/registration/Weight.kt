package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Weight : AppCompatActivity() {

    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var etWeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        btnPrevious = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)
        etWeight = findViewById(R.id.et_weight)

        // Button listeners
        btnPrevious.setOnClickListener {
            // Extract the entered weight
            val enteredWeight = etWeight.text.toString()

            // Retrieve the name, gender, age, and height from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")
            val height = intent.getStringExtra("height")

            // Prepare to navigate to the Height activity
            val intent = Intent(this@Weight, Height::class.java)

            // Pass the name, gender, age, height, and weight information to the next intent
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", enteredWeight)

            // Initiate the transition to the next activity
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            // Extract the entered weight
            val enteredWeight = etWeight.text.toString()

            // Retrieve the name, gender, age, and height from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")
            val height = intent.getStringExtra("height")

            // Prepare to navigate to the Goal activity
            val intent = Intent(this@Weight, Goal::class.java)

            // Pass the name, gender, age, height, and weight information to the next intent
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", enteredWeight)

            // Initiate the transition to the next activity
            startActivity(intent)
        }
    }
}
