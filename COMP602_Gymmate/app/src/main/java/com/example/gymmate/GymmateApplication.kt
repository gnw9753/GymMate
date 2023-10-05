package com.example.gymmate

import android.app.Application
import com.example.gymmate.data.AppContainer
import com.example.gymmate.data.AppDataContainer
import com.example.gymmate.data.userdata.User
import com.example.gymmate.data.userdata.UserInstance

class GymmateApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)

        UserInstance.currentUser = User(
            user_id = 0,
            user_email = "",
            user_name = "",
            user_gender = "",
            user_age = 0,
            user_height = 0f,
            user_weight = 0f,
            user_goal = "",
            user_days = emptyList(),
            exercise_schedule = emptyList(),
            isInitialized = false
        )
    }
}