package com.example.gymmate.caloriespage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CaloriesPageViewModel(exerciseRepository: ExerciseRepository): ViewModel()  {
    val caloriesPageUiState: StateFlow<CaloriesPageUiState> =

        exerciseRepository.getAllExercise().map { CaloriesPageUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = CaloriesPageUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var openBottomSheet by mutableStateOf(false)
    var skipPartiallyExpanded by mutableStateOf(false)
    var edgeToEdgeEnabled by mutableStateOf(false)
    var addWeightPressed by mutableStateOf(false)
    var addFoodPressed by mutableStateOf(false)

}

data class CaloriesPageUiState(val exerciseList: List<Exercise> = listOf()) {
}