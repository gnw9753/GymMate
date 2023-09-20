package com.example.gymmate.questionpage

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun QuestionPage(
    viewModel:QuestionPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Button(
        onClick = {
            coroutineScope.launch {
                //viewModel.insertExercise(context)
                onNavigateUp()
            }
        }
    ){
        Text(
            text = "Confirm"
        )
    }
}