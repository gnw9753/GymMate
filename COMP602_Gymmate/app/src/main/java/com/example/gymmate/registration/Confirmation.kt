package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Confirmation : AppCompatActivity() {

    private lateinit var btnPrevious: Button
    private lateinit var btnConfirm: Button
    private lateinit var tvNameCheck: TextView
    private lateinit var tvGenderCheck: TextView
    private lateinit var tvAgeCheck: TextView
    private lateinit var tvHeightCheck: TextView
    private lateinit var tvWeightCheck: TextView
    private lateinit var tvGoalCheck: TextView
    private lateinit var tvDaysCheck: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        btnPrevious = findViewById(R.id.btn_previous)
        btnConfirm = findViewById(R.id.btn_confirm)
        tvNameCheck = findViewById(R.id.tv_namecheck)
        tvGenderCheck = findViewById(R.id.tv_genderCheck)
        tvAgeCheck = findViewById(R.id.tv_ageCheck)
        tvHeightCheck = findViewById(R.id.tv_heightcheck)
        tvWeightCheck = findViewById(R.id.tv_weightcheck)
        tvGoalCheck = findViewById(R.id.tv_goalcheck)
        tvDaysCheck = findViewById(R.id.tv_dayscheck)

        // Retrieve the data from the intent extras
        val name = intent.getStringExtra("name")
        if (name != null) {
            tvNameCheck.text = "Your name: $name"
        }
        val gender = intent.getStringExtra("gender")
        if (gender != null) {
            tvGenderCheck.text = "Your gender: $gender"
        }
        val age = intent.getStringExtra("age")
        if (age != null) {
            tvAgeCheck.text = "Your age: $age"
        }
        val height = intent.getStringExtra("height")
        if (height != null) {
            tvHeightCheck.text = "Your height: $height cm"
        }
        val weight = intent.getStringExtra("weight")
        if (weight != null) {
            tvWeightCheck.text = "Your weight: $weight kg"
        }
        val selectedGoals = intent.getStringExtra("selectedGoals")
        if (selectedGoals != null) {
            tvGoalCheck.text = "Your Goal: $selectedGoals"
        }
        val days = intent.getStringExtra("daysChecked")
        if (days != null) {
            tvDaysCheck.text = "Workout days: $days"
        }

        // Button listeners
        btnPrevious.setOnClickListener {
            // Prepare to navigate to the Days activity
            val intent = Intent(this@Confirmation, Days::class.java)

            // Pass user information and selected days to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("selectedGoals", selectedGoals)
            intent.putExtra("daysChecked", days)

            // Initiate the transition to the Days activity
            startActivity(intent)
        }

        btnConfirm.setOnClickListener {
            try {
                val userModel = UserModel(
                    -1,
                    name!!,
                    gender!!,
                    age!!.toInt(),
                    height!!.toInt(),
                    weight!!.toInt(),
                    selectedGoals!!,
                    days!!
                )
                Toast.makeText(this@Confirmation, userModel.toString(), Toast.LENGTH_LONG).show()
                val dataBaseHelper = DataBaseHelper(this@Confirmation)

                // Display the result of registration after the confirm button is pressed
                if (dataBaseHelper.addOne(userModel)) {
                    Toast.makeText(this@Confirmation, "User successfully added", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@Confirmation, "Error adding user", Toast.LENGTH_LONG).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this@Confirmation, "Invalid input data", Toast.LENGTH_LONG).show()
            }

            val intent = Intent(this@Confirmation, Calories::class.java)
            intent.putExtra("age", age) // Pass the age to the next intent
            intent.putExtra("gender", gender) // Pass the gender to the next intent
            intent.putExtra("selectedGoals", selectedGoals) // Pass the goal to the next intent
            startActivity(intent)
        }
    }
}
