package com.example.gymmate.data.userdata

import androidx.room.Entity
import com.example.gymmate.data.exercisedata.ExerciseDay

// This is the data class that will be used to initialize all the data

data class User (
    var user_id: Int,
    var user_email: String,
    var user_name: String,
    var user_gender: String,
    var user_age: Int,
    var user_height: Float,
    var user_weight: Float,
    var user_goal: String,
    var user_days: List<Boolean>,
    var exercise_schedule: List<ExerciseDay>,
    var isInitialized: Boolean
    ){
}