package com.example.gymmate.data

object BasicWeekTestSample {

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

}