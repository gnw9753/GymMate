package com.example.gymmate.registrationpage
/*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymmatek.Screen

@Composable
fun EmailScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailHeader()
        EmailInputField(text, onTextChanged = { newText ->
            text = newText
            isEmailValid = isValidEmail(newText)
        })
        Spacer(modifier = Modifier.height(24.dp))
        EmailButtons(navController, text, isEmailValid)
    }
}

@Composable
private fun EmailHeader() {
    Text(
        text = "What is your email?",
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
private fun EmailInputField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Black),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {}
        )
    )
}

@Composable
private fun EmailButtons(
    navController: NavController,
    email: String,
    isEmailValid: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                navController.navigate(route = Screen.SignIn.route)
            },
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = "Cancel", fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isEmailValid) {
                    navController.navigate(route = "name")
                }
            },
            modifier = Modifier
                .width(200.dp),
            enabled = isEmailValid
        ) {
            Text(text = "Next", fontSize = 24.sp)
        }
    }
}

// Function to validate email format
private fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
    return email.matches(emailRegex)
}*/
