package com.example.gymmate.data.exercisedata

data class ExerciseDay (
    var day: String,
    var exerciseList: List<Exercise>,
    var isAvailable: Boolean,
){
}