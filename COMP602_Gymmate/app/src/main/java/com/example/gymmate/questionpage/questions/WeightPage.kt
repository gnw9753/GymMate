package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.gymmate.questionpage.QuestionPageViewModel

@Composable
fun WeightPage(viewModel: QuestionPageViewModel) {
    var isError by rememberSaveable { mutableStateOf(false) }
    val weightPattern = Regex("^\\d+(\\.\\d+)?$")

    fun validate(weightText: String) {
        isError = !weightText.matches(weightPattern)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
    ) {
        TextField(
            value = viewModel.weight,
            onValueChange = {
                viewModel.weight = it
            },
            label = { Text("Enter your weight") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = isError,
            keyboardActions = KeyboardActions(
                onDone = {
                    validate(viewModel.weight)
                    if (!isError) viewModel.increasePageIndex()
                }
            )
        )
        TextButton(
            onClick = {
                validate(viewModel.weight)
                //if(!isError)
                    viewModel.increasePageIndex()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Next")

        }
    }
}