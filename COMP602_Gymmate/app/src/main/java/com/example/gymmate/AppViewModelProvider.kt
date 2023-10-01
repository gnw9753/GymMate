package com.example.gymmate

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymmate.homepage.HomepageViewModel
import com.example.gymmate.questionpage.QuestionPageViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.gymmate.caloriespage.CaloriesPageViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomepageViewModel(gymmateApplication().container.exerciseRepository)
        }
        initializer {
            QuestionPageViewModel(gymmateApplication().container.exerciseRepository)
        }
        initializer {
            CaloriesPageViewModel(gymmateApplication().container.exerciseRepository)
        }
    }
}

fun CreationExtras.gymmateApplication(): GymmateApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GymmateApplication)
