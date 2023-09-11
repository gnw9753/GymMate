package com.example.gymmatekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Days : AppCompatActivity() {

    private lateinit var cbMonday: CheckBox
    private lateinit var cbTuesday: CheckBox
    private lateinit var cbWednesday: CheckBox
    private lateinit var cbThursday: CheckBox
    private lateinit var cbFriday: CheckBox
    private lateinit var cbSaturday: CheckBox
    private lateinit var cbSunday: CheckBox
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button

    private val daysChecked = BooleanArray(7) { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days)

        cbMonday = findViewById(R.id.cb_monday)
        cbTuesday = findViewById(R.id.cb_tuesday)
        cbWednesday = findViewById(R.id.cb_wednesday)
        cbThursday = findViewById(R.id.cb_thursday)
        cbFriday = findViewById(R.id.cb_friday)
        cbSaturday = findViewById(R.id.cb_saturday)
        cbSunday = findViewById(R.id.cb_sunday)
        btnPrevious = findViewById(R.id.btn_previous)
        btnNext = findViewById(R.id.btn_next)

        // Set up CheckBox listeners to update the boolean array
        val checkBoxes =
            arrayOf(cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday)
        checkBoxes.forEachIndexed { index, checkBox ->
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                daysChecked[index] = isChecked
            }
        }

        btnPrevious.setOnClickListener {
            // Get the selected days as a comma-separated string
            val selectedDays = getSelectedDaysAsString()

            // Retrieve user information from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")
            val height = intent.getStringExtra("height")
            val weight = intent.getStringExtra("weight")
            val selectedGoals = intent.getStringExtra("selectedGoals")

            // Prepare to navigate to the Goal activity
            val intent = Intent(this@Days, Goal::class.java)

            // Pass user information and selected days to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("selectedGoals", selectedGoals)
            intent.putExtra("daysChecked", selectedDays)

            // Initiate the transition to the Goal activity
            startActivity(intent)
        }

        btnNext.setOnClickListener {
            // Get the selected days as a comma-separated string
            val selectedDays = getSelectedDaysAsString()

            // Retrieve user information from the previous intent
            val name = intent.getStringExtra("name")
            val gender = intent.getStringExtra("gender")
            val age = intent.getStringExtra("age")
            val height = intent.getStringExtra("height")
            val weight = intent.getStringExtra("weight")
            val selectedGoals = intent.getStringExtra("selectedGoals")

            // Prepare to navigate to the Confirmation activity
            val intent = Intent(this@Days, Confirmation::class.java)

            // Pass user information and selected days to the next activity
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("height", height)
            intent.putExtra("weight", weight)
            intent.putExtra("selectedGoals", selectedGoals)
            intent.putExtra("daysChecked", selectedDays)

            // Initiate the transition to the Confirmation activity
            startActivity(intent)
        }
    }

    private fun getSelectedDaysAsString(): String {
        val selectedDaysList = mutableListOf<String>()

        // Define the day names in the same order as your boolean array
        val dayNames = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        for (i in 0 until daysChecked.size) {
            if (daysChecked[i]) {
                selectedDaysList.add(dayNames[i])
            }
        }

        // Convert the list of selected days to a comma-separated string
        return selectedDaysList.joinToString(", ")
    }

}
