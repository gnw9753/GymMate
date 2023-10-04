package com.example.gymmate.data

import android.content.Context
import com.example.gymmate.data.exercisedata.ExerciseRepository
import com.example.gymmate.data.exercisedata.OfflineExerciseRepository
import com.example.gymmate.data.userdata.OfflineUserEntityRepository
import com.example.gymmate.data.userdata.UserEntityRepository

interface AppContainer {
    val exerciseRepository: ExerciseRepository
    val userEntityRepository: UserEntityRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val exerciseRepository: ExerciseRepository by lazy {
        OfflineExerciseRepository(GymmateEmbeddedDatabase.getDatabase(context).exerciseDao())
    }

    override val userEntityRepository: UserEntityRepository by lazy {
        OfflineUserEntityRepository(GymmateEmbeddedDatabase.getDatabase(context).userEntityDao())
    }

}