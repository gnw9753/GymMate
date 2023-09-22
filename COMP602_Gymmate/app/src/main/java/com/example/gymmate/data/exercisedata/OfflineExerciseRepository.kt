package com.example.gymmate.data.exercisedata

import kotlinx.coroutines.flow.Flow

class OfflineExerciseRepository(private val exerciseDao: ExerciseDao): ExerciseRepository {


    override fun getAllExercise(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    override fun deleteAllExercise() = exerciseDao.deleteAllExercises()

    override suspend fun insertExercise(exercise: Exercise) = exerciseDao.insert(exercise)

    override suspend fun deleteExercise(exercise: Exercise) = exerciseDao.delete(exercise)

    override suspend fun updateExercise(exercise: Exercise) = exerciseDao.update(exercise)


}