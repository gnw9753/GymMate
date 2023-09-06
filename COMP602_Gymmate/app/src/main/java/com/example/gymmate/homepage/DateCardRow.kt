package com.example.gymmate.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymmate.data.ExerciseDay
import com.example.gymmate.ui.theme.gymmateTypography

@Composable
fun DateCardRow(day: String, exerciseList: List<ExerciseDay>, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        DayCard(day)
        ExerciseCard(exerciseList)
    }
}

@Composable
fun DayCard(day: String, modifier: Modifier = Modifier) {
    Card() {
        Column(modifier = Modifier) {
            Text(
                text = day,
                style = gymmateTypography.displayMedium,
            )
        }
    }
}

@Composable
fun ExerciseCard(exerciseList: List<ExerciseDay>, modifier: Modifier = Modifier) {
    Card() {
        Column(modifier = Modifier) {
            Text(
                text = "",
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    Homepage()
}