package com.example.gymmate.data

import android.content.Context
import com.example.gymmate.data.exercisedata.ExerciseRepository
import com.example.gymmate.data.exercisedata.OfflineExerciseRepository

interface AppContainer {
    val exerciseRepository: ExerciseRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val exerciseRepository: ExerciseRepository by lazy {
        OfflineExerciseRepository(GymmateEmbeddedDatabase.getDatabase(context).exerciseDao())
    }

}