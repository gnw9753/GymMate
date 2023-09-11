package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class Goal : AppCompatActivity() {

    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var cbMuscle: CheckBox
    private lateinit var cbWeight: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        btnPrevious = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)
        cbMuscle = findViewById(R.id.cb_muscle)
        cbWeight = findViewById(R.id.cb_weight)

        // Button listeners
        btnPrevious.setOnClickListener {
            // Prepare to capture selected goals
            var selectedGoals = ""

            // Check if the "Muscle Building" checkbox is selected
            if (cbMuscle.isChecked) {
                selectedGoals += cbMuscle.text.toString()
            }

            // Check if the "Weight Loss" checkbox is selected
            if (cbWeight.isChecked) {
                // Add a comma if "Muscle Building" is also selected
                if (selectedGoals.isNotEmpty()) {
                    selectedGoals += ", "
                }
                selectedGoals += cbWeight.text.toString()
            }

            // Retrieve data from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")
            val height = intent.getStringExtra("height")
            val weight = intent.getStringExtra("weight")

            // Prepare to navigate to the Weight activity
            val intent = Intent(this@Goal, Weight::class.java)

            // Pass collected data to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("selectedGoals", selectedGoals)

            // Initiate the transition to the next activity
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            // Prepare to capture selected goals
            var selectedGoals = ""

            // Check if the "Muscle Building" checkbox is selected
            if (cbMuscle.isChecked) {
                selectedGoals += cbMuscle.text.toString()
            }

            // Check if the "Weight Loss" checkbox is selected
            if (cbWeight.isChecked) {
                // Add a comma if "Muscle Building" is also selected
                if (selectedGoals.isNotEmpty()) {
                    selectedGoals += ", "
                }
                selectedGoals += cbWeight.text.toString()
            }

            // Retrieve data from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")
            val height = intent.getStringExtra("height")
            val weight = intent.getStringExtra("weight")

            // Prepare to navigate to the Days activity
            val intent = Intent(this@Goal, Days::class.java)

            // Pass collected data to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("selectedGoals", selectedGoals)

            // Initiate the transition to the next activity
            startActivity(intent)
        }
    }
}
