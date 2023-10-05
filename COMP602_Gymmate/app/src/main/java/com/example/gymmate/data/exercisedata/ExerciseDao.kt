package com.example.gymmate.data.exercisedata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao{

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getAllExercisesFromId(id: Int): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("DELETE FROM exercises")
    fun deleteAllExercises()

    // To support multi user functionality
    @Query("DELETE FROM exercises WHERE id = :id")
    fun deleteAllExerciseById(id: Int)

    @Query("Delete FROM exercises WHERE id = :id AND day = :day")
    fun deleteAllExerciseByIDAndDay(id: Int, day: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)
}