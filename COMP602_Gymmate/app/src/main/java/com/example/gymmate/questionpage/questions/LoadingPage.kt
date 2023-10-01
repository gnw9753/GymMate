package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymmate.questionpage.QuestionPageViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingPage(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {
    var loadingText by remember { mutableStateOf("Generating workout") }
    var dotsCount by remember { mutableStateOf(0) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Row() {
            Text(loadingText)
        }

        LaunchedEffect(Unit) {
            while (true) {
                loadingText = "Generating workout" + ".".repeat(dotsCount)
                dotsCount = (dotsCount + 1) % 4
                delay(500)
            }

        }
    }
}