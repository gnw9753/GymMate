package com.example.gymmate.data

data class ExerciseDay (
    val day: String,
    var exerciseList: List<Exercise>,
    var isAvailable: Boolean,
){
}