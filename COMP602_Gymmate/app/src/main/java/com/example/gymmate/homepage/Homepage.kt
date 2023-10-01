package com.example.gymmate.homepage

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider

@Composable
fun Homepage(
    viewModel: HomepageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val homePageUiState by viewModel.homePageUiState.collectAsState()
    homePageUiState.exerciseList
    var exerciseDayList = viewModel.exerciseListToExerciseDay()

    // Top horizontal slider // Carousel

    // Logic to decide to display homepage or exercise video page
    LazyColumn(modifier = modifier) {
        items(exerciseDayList) { exercise ->
            DateCardRow(
                day = exercise.day,
                exerciseDay = exercise,
                )
        }
    }
}
