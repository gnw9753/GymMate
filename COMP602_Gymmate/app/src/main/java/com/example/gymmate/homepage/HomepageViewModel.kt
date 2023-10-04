package com.example.gymmate.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDay
import com.example.gymmate.data.exercisedata.ExerciseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomepageViewModel(exerciseRepository: ExerciseRepository) : ViewModel() {

    val homePageUiState: StateFlow<HomePageUiState> =

        exerciseRepository.getAllExercise().map { HomePageUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomePageUiState()
            )

    fun exerciseListToExerciseDay(): List<ExerciseDay> {
        val exerciseList = homePageUiState.value.exerciseList
        var exerciseDayList: MutableList<ExerciseDay> = mutableListOf()
        val dayString =
            listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        // most inefficient algo???
        // Should delete the exercises when i add them to the temp list
        // So i dont have to check the whole list every time
        // But im low on time
        // Should learn how to use map as well
        for(day in dayString) {
            var tempExerciseList: MutableList<Exercise> = mutableListOf()
            for(exercise in exerciseList) {
                if(exercise.day.equals(day, ignoreCase = true)) {
                    tempExerciseList.add(exercise)
                }
            }
            exerciseDayList.add(ExerciseDay(day,tempExerciseList,tempExerciseList.isNotEmpty()))
        }
        return exerciseDayList
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class HomePageUiState(val exerciseList: List<Exercise> = listOf()) {
}

