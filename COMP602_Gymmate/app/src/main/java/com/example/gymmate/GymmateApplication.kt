package com.example.gymmate

import android.app.Application
import com.example.gymmate.data.AppContainer
import com.example.gymmate.data.AppDataContainer

class GymmateApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}