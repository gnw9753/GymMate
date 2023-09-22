package com.example.gymmate.data.exercisedata

import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    fun getAllExercise(): Flow<List<Exercise>>
    fun deleteAllExercise()
    suspend fun insertExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)
}