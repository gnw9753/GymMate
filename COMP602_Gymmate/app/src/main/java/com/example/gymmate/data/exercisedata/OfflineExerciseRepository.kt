package com.example.gymmate.data.exercisedata

import com.example.gymmate.data.userdata.UserEntity
import kotlinx.coroutines.flow.Flow

class OfflineExerciseRepository(private val exerciseDao: ExerciseDao): ExerciseRepository {


    override fun getAllExerciseById(id: Int): Flow<List<Exercise>>  = exerciseDao.getAllExercisesFromId(id)

    override fun getAllExercise(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    override fun deleteAllExercise() = exerciseDao.deleteAllExercises()

    override fun deleteAllExerciseById(id: Int) = exerciseDao.deleteAllExerciseById(id)

    override fun deleteAllExerciseByIDAndDay(id: Int, day: String) = exerciseDao.deleteAllExerciseByIDAndDay(id, day)

    override suspend fun insertExercise(exercise: Exercise) = exerciseDao.insert(exercise)

    override suspend fun deleteExercise(exercise: Exercise) = exerciseDao.delete(exercise)

    override suspend fun updateExercise(exercise: Exercise) = exerciseDao.update(exercise)

}