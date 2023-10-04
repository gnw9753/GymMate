package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
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
import androidx.lifecycle.ViewModel
import com.example.gymmate.questionpage.QuestionPageViewModel

@Composable
fun NamePage(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {

    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        println("validate!")
        val regex = "^[A-Za-z ]+$".toRegex()
        isError = !regex.matches(text)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
    ) {

        TextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Enter your name") },
            placeholder = { Text("john") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            isError = isError,
            keyboardActions = KeyboardActions(
                onDone = {
                    validate(viewModel.name)
                    if(!isError)viewModel.increasePageIndex()
                }
            )
        )
        TextButton(
            onClick = {
                validate(viewModel.name)
                if(!isError) viewModel.increasePageIndex()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Next")
        }
    }
}

