package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gymmate.questionpage.QuestionPageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoadingPage(
    viewModel: QuestionPageViewModel,
    navigateToInitializeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var loadingText by remember { mutableStateOf("Generating workout") }
    var dotsCount by remember { mutableIntStateOf(0) }
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
            coroutineScope.launch {
                while (true) {
                    loadingText = "Generating workout" + ".".repeat(dotsCount)
                    dotsCount = (dotsCount + 1) % 4
                    delay(500)
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.createUserProfile(context)
            delay(2000)
            navigateToInitializeScreen()
        }
    }
}