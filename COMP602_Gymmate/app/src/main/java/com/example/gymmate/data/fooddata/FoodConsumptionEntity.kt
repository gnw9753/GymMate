package com.example.gymmate.data.fooddata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_consumption")
data class FoodConsumptionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val foodName: String,
    val grams: Float,
    val calories: Float,
    val protein: Float,
    val fat: Float,
    val carbs: Float
)
