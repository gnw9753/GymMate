package com.example.gymmate.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.R
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    navigateToInitializeUser: () -> Unit,
    navigateToQuestion: () -> Unit,
    loginPageViewModel: LoginPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(loginPageViewModel.emailFound) {
        if (loginPageViewModel.emailFound) {
            navigateToInitializeUser()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Gymmate Logo",
            modifier = Modifier.size(48.dp)
        )
        TextField(
            value = loginPageViewModel.emailEntered,
            onValueChange = { loginPageViewModel.emailEntered = it },
            label = { Text("Enter your email") },
            placeholder = { Text("john@gmail.com") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            isError = loginPageViewModel.isError,
            keyboardActions = KeyboardActions(
                onDone = {
                    coroutineScope.launch {
                        loginPageViewModel.checkEmailInDatabase()
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    loginPageViewModel.checkEmailInDatabase()
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)

        ) {
            Text("Sign In")
        }
        Row() {
            Text(
                text = "Don't have an account? ",
                modifier = Modifier
                    .padding(top = 12.dp)
            )
            TextButton(onClick = {
                navigateToQuestion()
            }) {
                Text("Sign Up")
            }
        }

    }
}