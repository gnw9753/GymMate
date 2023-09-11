package com.example.gymmate.homepage

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gymmate.data.BasicWeekTestSample.workoutScheduleList

@Composable
fun Homepage(modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(workoutScheduleList) { item ->
            DateCardRow(
                day = item.day,
                exerciseDay = item,
                )
        }
    }
}
