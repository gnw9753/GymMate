package com.example.gymmatekotlin


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Gender : AppCompatActivity() {

    private lateinit var tvGender: TextView
    private lateinit var btnMale: Button
    private lateinit var btnFemale: Button
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        tvGender = findViewById(R.id.tv_gender)
        btnMale = findViewById(R.id.btn_male)
        btnFemale = findViewById(R.id.btn_female)
        btnPrevious = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)

        // Button listeners
        btnMale.setOnClickListener {
            tvGender.text = "Male"
        }

        btnFemale.setOnClickListener {
            tvGender.text = "Female"
        }

        btnPrevious.setOnClickListener {
            // Get the entered gender from the TextView
            val enteredGender = tvGender.text.toString()

            // Get the name from the previous intent
            val name = intent.getStringExtra("name")

            // Prepare to navigate to the MainActivity
            val intent = Intent(this@Gender, MainActivity::class.java)

            // Pass the name and gender information to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", enteredGender)

            // Initiate the activity transition
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            // Get the entered gender from the TextView
            val enteredGender = tvGender.text.toString()

            // Get the name from the previous intent
            val name = intent.getStringExtra("name")

            // Prepare to navigate to the Age activity
            val intent = Intent(this@Gender, Age::class.java)

            // Pass the name and gender information to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", enteredGender)

            // Initiate the activity transition
            startActivity(intent)
        }
    }
}