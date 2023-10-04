package com.example.gymmate.questionpage.questions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
fun EmailPage(
    viewModel: QuestionPageViewModel
){
    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        isError = !text.matches(emailRegex)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
    ) {

        TextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text(if(isError)"Enter your email*" else "Enter your email") },
            placeholder = { Text("john@gmail.com") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            isError = isError,
            keyboardActions = KeyboardActions(
                onDone = {
                    validate(viewModel.email)
                    if(!isError)viewModel.increasePageIndex()
                }
            )
        )
        TextButton(
            onClick = {
                validate(viewModel.email)
                if(!isError)
                    viewModel.increasePageIndex()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Next")
        }
    }

}