package com.example.gymmate.data.userdata

import androidx.room.Entity
import com.example.gymmate.data.exercisedata.ExerciseDay

data class User (
    var user_email: String,
    var user_name: String,
    var user_gender: String,
    var user_age: Int,
    var user_height: Int,
    var user_weight: Int,
    var user_goal: String,
    var user_days: List<Boolean>,
    var exercise_schedule: List<ExerciseDay>
    ){
}