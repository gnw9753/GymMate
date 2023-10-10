package com.example.gymmate.homepage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gymmate.data.logindata.LoginEntity
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.gymmate.ui.theme.Typography

@Composable
fun TopBarSwap(
    loginEntity: LoginEntity,
    viewModel: HomepageViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 5.dp, end = 5.dp)
    ) {
        BarSwap(viewModel = viewModel)
        if (viewModel.displayTutorial) {
            VideoSliderTopbar(viewModel = viewModel)
        } else {
            DayDisplayTopBar(
                loginEntity,
                viewModel
            )
        }
    }
}

@Composable
fun BarSwap(
    viewModel: HomepageViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DisplayCurrentDay(viewModel = viewModel)
        Spacer(modifier = Modifier.weight(1f))
        SwapButtons(viewModel = viewModel)
    }
}

@Composable
fun DisplayCurrentDay(
    viewModel: HomepageViewModel,
    modifier: Modifier = Modifier
) {
    val dayNameFormat = SimpleDateFormat("E", Locale.getDefault())
    val dayNumberFormat = SimpleDateFormat("dd", Locale.getDefault())

    val dayNameFormatted = dayNameFormat.format(viewModel.currentDate.time)
    val dayNumberFormatted = dayNumberFormat.format(viewModel.currentDate.time)

    Row(
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = dayNameFormatted,
            style = Typography.headlineMedium,
            modifier = Modifier
                .alpha(0.8f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = dayNumberFormatted,
            style = Typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(bottom = 5.dp)
        )
    }
}

@Composable
fun SwapButtons(viewModel: HomepageViewModel, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
            .fillMaxWidth()
    ) {
        FilledTonalButton(
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            enabled = viewModel.displayTutorial,
            onClick = {
                viewModel.displayTutorial = !viewModel.displayTutorial
            },
            modifier = Modifier
                .padding(end = 5.dp)
        ) {
            Text(
                text = "daily",
                modifier = Modifier
            )
        }
        FilledTonalButton(
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            enabled = !viewModel.displayTutorial,
            onClick = {
                viewModel.displayTutorial = !viewModel.displayTutorial
            },
            modifier = Modifier
                .padding(end = 5.dp)
        ) {
            Text(
                text = "tutorial",
                modifier = Modifier
            )
        }

    }
}
