package com.example.gymmate.data

import androidx.room.Entity

@Entity(tableName = "users")
data class User (
    var name: String,
    var height: Float,
    var weight: Float,
    var age: Int,
    var difficulty: Int,
    var daysAvailable: List<String>,
    var caloriesRequired: Int,
    var exerciseList: List<ExerciseDay>,
    var bmi: Float = weight / (height*height)
    ){
}