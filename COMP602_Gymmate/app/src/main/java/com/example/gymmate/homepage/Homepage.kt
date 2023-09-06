package com.example.gymmate.homepage

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gymmate.data.Exercise
import com.example.gymmate.data.ExerciseDay

@Composable
fun Homepage(modifier: Modifier = Modifier) {
    val exerciseList = listOf(
        Exercise(
            exerciseName = "Deadlift",
            difficulty = 3,
            isPush = false,
            isPull = true,
            modality = 1,
            joint = 1,
            muscle = "Back"
        )
    )

    val workoutScheduleList = listOf(
        ExerciseDay(
            day = "Monday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
        ExerciseDay(
            day = "Tuesday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
        ExerciseDay(
            day = "Wednesday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
        ExerciseDay(
            day = "Thursday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
        ExerciseDay(
            day = "Friday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
        ExerciseDay(
            day = "Saturday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
        ExerciseDay(
            day = "Sunday",
            exerciseList = exerciseList,
            isAvailable = true,
        ),
    )

    LazyColumn(modifier = modifier) {
        items(workoutScheduleList) { item ->
        }
    }
}
