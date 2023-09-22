package com.example.gymmate.data.exercisedata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    var day: String,
    val muscleGroup: String,
    @PrimaryKey(autoGenerate = false)
    val exerciseName: String,
    val difficulty: String,
    val ulc: String,
    val pp: String,
    val modality: String,
    val joint: String,
) {
    /*
    val setAndRepRange: String = when(difficulty){
        "Beginner" -> SetAndRepRange().beginnerSet
        "Intermediate" -> SetAndRepRange().intermediateSet
        "Advanced" -> SetAndRepRange().advancedSet
        else -> SetAndRepRange().untilFailure
    }*/
}
