package com.example.gymmate.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.data.userdata.UserInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InitializeUserPage(
    navigateToHomePage: () -> Unit,
    initializeUserPageViewModel: InitializeUserPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    var loadingText by remember { mutableStateOf("") }
    var dotsCount by remember { mutableIntStateOf(0) }

    println("loading page")

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
                loadingText = "Loading your profile" + ".".repeat(dotsCount)
                dotsCount = (dotsCount + 1) % 4
                delay(500)
            }
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            initializeUserPageViewModel.initializeUserProfile()
            delay(2000)
            navigateToHomePage()
        }
    }
}
