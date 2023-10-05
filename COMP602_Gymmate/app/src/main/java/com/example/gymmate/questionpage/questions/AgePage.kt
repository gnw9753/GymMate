package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import com.example.gymmate.questionpage.QuestionPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgePage(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {
    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(ageText: String) {
        isError = ageText.isEmpty()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
    ) {

        TextField(
            value = viewModel.age,
            onValueChange = {
                if (it.isEmpty() || it.isDigitsOnly()) {
                    viewModel.age = it
                }
            },
            label = { Text("Enter your age") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = isError,
            keyboardActions = KeyboardActions(
                onDone = {
                    validate(viewModel.age)
                    if(!isError)viewModel.increasePageIndex()
                }
            )
        )
        TextButton(
            onClick = {
                validate(viewModel.age)
                if(!isError) viewModel.increasePageIndex()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Next")

        }
    }

}