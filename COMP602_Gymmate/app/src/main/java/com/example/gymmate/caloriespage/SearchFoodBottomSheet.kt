package com.example.gymmate.caloriespage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.gymmate.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFoodBottomSheet(
    viewModel: CaloriesPageViewModel,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = viewModel.skipPartiallyExpanded
    )
    if (viewModel.openBottomSheet) {
        val windowInsets = if (viewModel.edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets
        Column(
            modifier = Modifier
        ) {
            Spacer(Modifier.weight(0.25f))
            BottomSheet(viewModel = viewModel, scope = scope, windowInsets, bottomSheetState)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    viewModel: CaloriesPageViewModel,
    scope: CoroutineScope,
    windowInsets: WindowInsets,
    bottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.openBottomSheet = false },
        sheetState = bottomSheetState,
        windowInsets = windowInsets
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            DisplayFoodItemBottomSheet(
                viewModel = viewModel
            )
            ConfirmAddButton(
                viewModel = viewModel,
                scope = scope,
                bottomSheetState = bottomSheetState
            )
        }
    }
}


@Composable
fun DisplayFoodItemBottomSheet(viewModel: CaloriesPageViewModel, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(
            viewModel.foodItem.foodName,
            style = Typography.headlineMedium
        )
        Divider(Modifier.padding(10.dp))
        DisplayFoodItemStat(viewModel = viewModel)
        DisplayFoodItemSlider(viewModel = viewModel)
    }
}

fun Float.round(decimals: Int): Float = "%.${decimals}f".format(this).toFloat()

@Composable
fun DisplayFoodItemSlider(viewModel: CaloriesPageViewModel, modifier: Modifier = Modifier) {
    Slider(
        value = viewModel.foodItemSlider,
        onValueChange = {
            viewModel.foodItemSlider = it
            viewModel.foodItemCalories = (viewModel.foodItem.calories / viewModel.foodItemGram) * it
            viewModel.foodItemCarb = (viewModel.foodItem.carbs / viewModel.foodItemGram) * it
            viewModel.foodItemProtein = (viewModel.foodItem.protein / viewModel.foodItemGram) * it
            viewModel.foodItemFat = (viewModel.foodItem.fat / viewModel.foodItemGram) * it
            viewModel.foodItemCalories = viewModel.foodItemCalories.round(2)
            viewModel.foodItemCarb = viewModel.foodItemCarb.round(2)
            viewModel.foodItemProtein = viewModel.foodItemProtein.round(2)
            viewModel.foodItemFat = viewModel.foodItemFat.round(2)
        },
        valueRange = 50f..250f,
        steps = 3,
        modifier = Modifier
            .padding(5.dp)
    )
}

@Composable
fun DisplayFoodItemStat(viewModel: CaloriesPageViewModel, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
            .fillMaxWidth()
    ) {
        DisplayFoodItemText(
            viewModel.foodItemFat.toString(),
            "Fat",
            Typography.titleSmall,
            Modifier.weight(0.5f)
        )
        DisplayFoodItemText(
            viewModel.foodItemCalories.toString(),
            "Cal",
            Typography.titleMedium,
            Modifier.weight(1f)
        )
        DisplayFoodItemText(
            viewModel.foodItemSlider.toString(),
            "g",
            Typography.titleLarge,
            Modifier.weight(1f)
        )
        DisplayFoodItemText(
            viewModel.foodItemProtein.toString(),
            "Protein",
            Typography.titleMedium,
            Modifier.weight(1f)
        )
        DisplayFoodItemText(
            viewModel.foodItemCarb.toString(),
            "Carb",
            Typography.titleSmall,
            Modifier.weight(0.5f)
        )
    }
}

@Composable
fun DisplayFoodItemText(
    text: String,
    type: String,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = style,
        )
        Text(
            text = type,
            style = style,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmAddButton(
    viewModel: CaloriesPageViewModel,
    scope: CoroutineScope,
    bottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                if (!bottomSheetState.isVisible) {
                    viewModel.openBottomSheet = false
                }
            }
        }
    ) {
        Text("Confirm")
    }
}


