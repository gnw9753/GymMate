package com.example.gymmate.caloriespage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gymmate.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWeightBottomSheet(viewModel: CaloriesPageViewModel, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = viewModel.skipPartiallyExpanded
    )

    if (viewModel.openWeightBottomSheet) {
        val windowInsets = if (viewModel.edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets

        WeightBottomSheet(
            viewModel = viewModel,
            scope = scope,
            windowInsets = windowInsets,
            bottomSheetState = bottomSheetState
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightBottomSheet(
    viewModel: CaloriesPageViewModel,
    scope: CoroutineScope,
    windowInsets: WindowInsets,
    bottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.openWeightBottomSheet = false },
        sheetState = bottomSheetState,
        windowInsets = windowInsets
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
                modifier = Modifier.fillMaxWidth() // Fill the maximum width
            ) {
                Text(
                    text = "Your weight is"
                )
                Text(
                    text = (viewModel.pickedWeight.toString() + " kg"),
                    textAlign = TextAlign.Center, // Center the text horizontally
                    style = Typography.titleLarge
                )
                Slider(
                    value = viewModel.pickedWeight,
                    onValueChange = { newValue ->
                        // Round to the nearest 0.5
                        val roundedValue = (newValue * 2).roundToInt() / 2.0
                        viewModel.pickedWeight = roundedValue.toFloat()
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    valueRange = 0f..100f,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center, // Center-align buttons horizontally
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                viewModel.openWeightBottomSheet = false
                            }
                        }
                    }) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(16.dp)) // Add space between buttons
                    Button(onClick = {
                        scope.launch {
                            viewModel.addWeightToDatabase()
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                viewModel.openWeightBottomSheet = false
                            }
                        }
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}
