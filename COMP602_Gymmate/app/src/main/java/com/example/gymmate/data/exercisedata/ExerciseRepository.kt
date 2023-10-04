package com.example.gymmate.data.exercisedata

import com.example.gymmate.data.userdata.UserEntity
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    // Exercise DAO
    fun getAllExerciseById(id: Int): Flow<List<Exercise>>
    fun getAllExercise(): Flow<List<Exercise>>
    fun deleteAllExercise()
    fun deleteAllExerciseById(id: Int)
    fun deleteAllExerciseByIDAndDay(id: Int, day: String)
    suspend fun insertExercise(exercise: Exercise)
    suspend fun deleteExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)

}