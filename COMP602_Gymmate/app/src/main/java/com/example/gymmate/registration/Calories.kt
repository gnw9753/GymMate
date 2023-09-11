package com.example.gymmatekotlin

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.collections.ArrayList

class Calories : AppCompatActivity() {

    private lateinit var btnAddFood: Button
    private lateinit var btnAddWeight: Button
    private lateinit var btnCancel1: Button
    private lateinit var btnCancel2: Button
    private lateinit var btnAdd1: Button
    private lateinit var btnClear: Button
    private lateinit var btnDelete: Button
    private lateinit var btnConfirm1: Button
    private lateinit var tvCalDisplay: TextView
    private lateinit var selectedValueTextView: TextView
    private lateinit var tvProtein: TextView
    private lateinit var tvCarbs: TextView
    private lateinit var tvFat: TextView
    private lateinit var tvWeight: TextView
    private lateinit var cvWeightPanel: CardView
    private lateinit var cvFoodPanel: CardView
    private lateinit var numberPicker: NumberPicker
    private lateinit var lvFoodList: ListView
    private lateinit var lvLatestList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)

        tvCalDisplay = findViewById(R.id.tv_calDisplay)
        btnAddFood = findViewById(R.id.btn_addFood)
        btnAddWeight = findViewById(R.id.btn_addWeight)
        btnCancel1 = findViewById(R.id.btn_cancel1)
        btnCancel2 = findViewById(R.id.btn_cancel2)
        btnAdd1 = findViewById(R.id.btn_add1)
        btnClear = findViewById(R.id.btn_clear)
        btnDelete = findViewById(R.id.btn_delete)
        btnConfirm1 = findViewById(R.id.btn_confirm1)
        val piechart = findViewById<PieChart>(R.id.piechart)
        tvProtein = findViewById(R.id.tv_protein)
        tvCarbs = findViewById(R.id.tv_carbs)
        tvFat = findViewById(R.id.tv_fat)
        tvWeight = findViewById(R.id.tv_weight)
        cvWeightPanel = findViewById(R.id.cv_weightpanel)
        cvFoodPanel = findViewById(R.id.cv_foodpanel)
        numberPicker = findViewById(R.id.numberPicker)
        selectedValueTextView = findViewById(R.id.selectedValueTextView)
        lvFoodList = findViewById(R.id.lv_foodList)
        lvLatestList = findViewById(R.id.lv_latestList)

        val gender = intent.getStringExtra("gender")
        val age = intent.getStringExtra("age")
        val selectedGoals = intent.getStringExtra("selectedGoals")

        val selectedFoodList: ArrayList<FoodModel> = ArrayList()

        numberPicker.minValue = 0
        numberPicker.maxValue = 1000

        // Set up a custom formatter to display float values
        val formatter = NumberPicker.Formatter { value ->
            // Convert the integer value to a float and format it as needed
            val floatValue = value / 10.0f
            String.format("%.1f", floatValue)
        }

        numberPicker.setFormatter(formatter)

        if (gender != null && age != null && selectedGoals != null) {
            val intAge = age.toInt()
            var calorieValue = 0.0

            if (gender == "Male") {
                calorieValue = when {
                    intAge <= 14 -> (if (selectedGoals == "Building muscle") {
                        2500 * 1.1
                    } else {
                        2500 - 500
                    }) as Double
                    intAge <= 18 -> (if (selectedGoals == "Building muscle") {
                        3000 * 1.1
                    } else {
                        3000 - 500
                    }) as Double
                    intAge <= 24 -> (if (selectedGoals == "Building muscle") {
                        2900 * 1.1
                    } else {
                        2900 - 500
                    }) as Double
                    intAge <= 50 -> (if (selectedGoals == "Building muscle") {
                        2900 * 1.1
                    } else {
                        2900 - 500
                    }) as Double
                    intAge >= 51 -> (if (selectedGoals == "Building muscle") {
                        3000 * 1.1
                    } else {
                        3000 - 500
                    }) as Double
                    else -> calorieValue
                }
            } else {
                calorieValue = when {
                    intAge <= 14 -> (if (selectedGoals == "Building muscle") {
                        2200 * 1.1
                    } else {
                        2200 - 500
                    }) as Double
                    intAge <= 18 -> (if (selectedGoals == "Building muscle") {
                        2200 * 1.1
                    } else {
                        2200 - 500
                    }) as Double
                    intAge <= 24 -> (if (selectedGoals == "Building muscle") {
                        2200 * 1.1
                    } else {
                        2200 - 500
                    }) as Double
                    intAge <= 50 -> (if (selectedGoals == "Building muscle") {
                        2200 * 1.1
                    } else {
                        2200 - 500
                    }) as Double
                    intAge >= 51 -> (if (selectedGoals == "Building muscle") {
                        1900 * 1.1
                    } else {
                        1900 - 500
                    }) as Double
                    else -> calorieValue
                }
            }

            // Format the calorieValue for display
            val calorieDisplay = String.format("%.2f cal", calorieValue)
            tvCalDisplay.text = calorieDisplay

            // Calculating nutrition intake
            tvProtein.text = "10~30% ${calculateValue(calorieValue, 10, 4)} ~ ${calculateValue(
                calorieValue,
                30,
                4
            )} grams Protein"
            tvCarbs.text = "45~65% ${calculateValue(calorieValue, 45, 4)} ~ ${calculateValue(
                calorieValue,
                65,
                4
            )} grams Carbs"
            tvFat.text = if (intAge >= 51) {
                "10~30% ${calculateValue(calorieValue, 10, 9) * 2} ~ ${calculateValue(
                    calorieValue,
                    30,
                    9
                )} grams Fat"
            } else {
                "10~30% ${calculateValue(calorieValue, 10, 9)} ~ ${calculateValue(
                    calorieValue,
                    30,
                    9
                )} grams Fat"
            }

            // Set the data and color to the pie chart
            piechart.addPieSlice(
                PieModel(
                    "Protein",
                    30f,
                    resources.getColor(R.color.protein)
                )
            )
            piechart.addPieSlice(
                PieModel(
                    "Carbs",
                    40f,
                    resources.getColor(R.color.carbs)
                )
            )
            piechart.addPieSlice(
                PieModel(
                    "Fat",
                    30f,
                    resources.getColor(R.color.fat)
                )
            )

            // To animate the pie chart
            piechart.startAnimation()
        }

        // Add click listeners to your buttons
        btnAddFood.setOnClickListener {
            // Toggle the visibility of the foodpanel
            if (cvFoodPanel.visibility == View.VISIBLE) {
                cvFoodPanel.visibility = View.GONE
            } else {
                cvFoodPanel.visibility = View.VISIBLE
                val dataBaseHelper = FoodDatabaseHelper(this@Calories)
                val everyfood = dataBaseHelper.everyfood
                val userArrayAdapter =
                    ArrayAdapter(this@Calories, android.R.layout.simple_list_item_multiple_choice, everyfood)
                lvFoodList.adapter = userArrayAdapter
            }
        }

        btnAddWeight.setOnClickListener {
            // Toggle the visibility of the weightpanel
            if (cvWeightPanel.visibility == View.VISIBLE) {
                cvWeightPanel.visibility = View.GONE
            } else {
                cvWeightPanel.visibility = View.VISIBLE
            }
        }

        btnCancel1.setOnClickListener {
            // Toggle the visibility of the weightpanel
            if (cvWeightPanel.visibility == View.VISIBLE) {
                cvWeightPanel.visibility = View.GONE
            }
        }

        btnCancel2.setOnClickListener {
            // Toggle the visibility of the foodpanel
            if (cvFoodPanel.visibility == View.VISIBLE) {
                cvFoodPanel.visibility = View.GONE
            }
        }

        btnAdd1.setOnClickListener {
            // Loop through the items in lvFoodList
            val itemCount = lvFoodList.count
            for (i in 0 until itemCount) {
                if (lvFoodList.isItemChecked(i)) {
                    // If the item is checked, add it to the selectedFoodList
                    selectedFoodList.add(lvFoodList.getItemAtPosition(i) as FoodModel)
                }
            }

            // Create an adapter for lvLatestList with the selectedFoodList
            val latestListAdapter =
                ArrayAdapter(this@Calories, android.R.layout.simple_list_item_multiple_choice, selectedFoodList)

            // Set the adapter for lvLatestList
            lvLatestList.adapter = latestListAdapter

            // Notify the adapter that the data has changed
            latestListAdapter.notifyDataSetChanged()

            // Toggle the visibility of the foodpanel
            cvFoodPanel.visibility = View.GONE
        }

        btnDelete.setOnClickListener {
            // Create a list to store the items to be deleted
            val itemsToDelete: MutableList<FoodModel> = ArrayList()

            // Loop through the items in lvLatestList
            val itemCount = lvLatestList.count
            for (i in 0 until itemCount) {
                if (lvLatestList.isItemChecked(i)) {
                    // If the item is checked, add it to the itemsToDelete
                    itemsToDelete.add(lvLatestList.getItemAtPosition(i) as FoodModel)
                }
            }

            // Remove the selected items from selectedFoodList
            selectedFoodList.removeAll(itemsToDelete)

            // Notify the adapter that the data has changed
            (lvLatestList.adapter as ArrayAdapter<FoodModel>).notifyDataSetChanged()
        }

        btnClear.setOnClickListener {
            // Clear all items from selectedFoodList
            selectedFoodList.clear()

            // Notify the adapter that the data has changed
            (lvLatestList.adapter as ArrayAdapter<FoodModel>).notifyDataSetChanged()
        }

        btnConfirm1.setOnClickListener {
            // Get the selected value from the NumberPicker
            val selectedValue = numberPicker.value / 10.0f

            // Format the selected value as needed
            val formattedValue = String.format("%.1f", selectedValue)

            // Update the tvWeight TextView with the selected value
            tvWeight.text = "$formattedValue kg"

            // Hide the weight panel or perform any other necessary actions
            cvWeightPanel.visibility = View.GONE
        }
    }

    private fun calculateValue(doubleValue: Double, rate: Int, divideby: Int): Int {
        // Calculate the result
        return ((rate * doubleValue) / 100 / divideby).toInt()
    }
}
