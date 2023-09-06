package com.example.gymmate.data

data class User (
    var name: String,
    var height: Float,
    var weight: Float,
    var age: Int,
    var difficulty: Int,
    var daysAvailable: List<String>,
    var caloriesRequired: Int,
    var exerciseList: List<ExerciseDay>,
    ){
    val bmi: Float = weight / (height*height)
}