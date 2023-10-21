package com.example.gymmate.caloriespage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.ui.theme.Typography
import com.example.gymmate.ui.theme.calories
import com.example.gymmate.ui.theme.noCalories

@Composable
fun CaloriesPage(
    viewModel: CaloriesPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    viewModel.loadCSV(context)
    val foodEntityUiState by viewModel.foodEntityUiState.collectAsState()
    viewModel.calculateNutrition(foodEntityUiState.foodConsumptionList)
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            if (viewModel.displayAddFood) {
                SearchFoodPage(
                    viewModel = viewModel,
                )
            } else {
                PieCard(
                    viewModel,
                    modifier = Modifier.weight(0.31f)
                )
                LatestCard(foodEntityUiState.foodConsumptionList, modifier = Modifier.weight(0.31f))
                QuickAddCard(
                    foodEntityUiState.foodConsumptionList,
                    viewModel,
                    modifier = Modifier.weight(0.31f)
                )
                FoodWeightButton(viewModel = viewModel, modifier = Modifier.weight(0.07f))
                AddWeightBottomSheet(viewModel = viewModel)
            }

        }
        /*
        GymmateNavigationBar(
            selectedDestination = GymmateRoute.CALORIES,
            navigateToTopLevelDestination = navigationActions::navigateTo
        )*/
    }
}


@Composable
fun FoodWeightButton(
    viewModel: CaloriesPageViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Button(
            onClick = {
                viewModel.displayAddFood = true
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(end = 5.dp)
        ) {
            Text(text = "Add Food")
        }
        Button(
            onClick = {
                viewModel.openWeightBottomSheet = !viewModel.openWeightBottomSheet
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(start = 5.dp)
        ) {
            Text(text = "Add Weight")
        }
    }
}


///////////////////////
// Start of Pie Card //
///////////////////////
@Composable
fun PieCard(
    viewModel: CaloriesPageViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
        ) {
            viewModel.calculateCalorieRequirement()

            val entries = listOf(
                PieChartEntry(calories, (viewModel.totalCalories / viewModel.caloriesRequired)),
                PieChartEntry(
                    noCalories,
                    ((viewModel.caloriesRequired - viewModel.totalCalories) / viewModel.caloriesRequired)
                )
            )
            PieChart(entries)
            PieCardText(viewModel)
        }

    }
}

@Composable
fun PieCardText(
    viewModel: CaloriesPageViewModel,
) {
    if (viewModel.caloriesRequired > 0) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            // Header
            DisplayText(displayText = "Calories Needed", typography = Typography.headlineSmall)
            DisplayText(
                displayText = (viewModel.totalCalories.toString() + " / " + viewModel.caloriesRequired.toString()),
                typography = Typography.bodyMedium
            )
            DisplayText(displayText = "Macros", typography = Typography.headlineSmall)
            DisplayText(
                displayText = viewModel.totalProtein.toString() + " | " + calculateValue(
                    viewModel.caloriesRequired.toInt(),
                    10,
                    4
                ).toString() + " ~ " + calculateValue(
                    viewModel.caloriesRequired.toInt(),
                    30,
                    4
                ).toString() + "g Protein",
                typography = Typography.bodyMedium
            )
            DisplayText(
                displayText = viewModel.totalCarbs.toString() + " | " + calculateValue(
                    viewModel.caloriesRequired.toInt(),
                    45,
                    4
                ).toString() + " ~ " + calculateValue(
                    viewModel.caloriesRequired.toInt(),
                    65,
                    4
                ).toString() + "g Carbs",
                typography = Typography.bodyMedium
            )
            if (viewModel.getAge() >= 51) {
                DisplayText(
                    displayText = viewModel.totalFat.toString() + " | " + calculateValue(
                        viewModel.caloriesRequired.toInt(),
                        20,
                        9
                    ).toString() + " ~ " + calculateValue(
                        viewModel.caloriesRequired.toInt(),
                        35,
                        9
                    ).toString() + "g Fat",
                    typography = Typography.bodyMedium
                )
            } else {
                DisplayText(
                    displayText = viewModel.totalFat.toString() + " | " + calculateValue(
                        viewModel.caloriesRequired.toInt(),
                        20,
                        9
                    ).toString() + " ~ " + calculateValue(
                        viewModel.caloriesRequired.toInt(),
                        35,
                        9
                    ).toString() + "g Fat",
                    typography = Typography.bodyMedium
                )
            }
        }
    }
}

/////////////////////
// End of Pie card //
/////////////////////


@Composable
fun DisplayText(displayText: String, typography: TextStyle, modifier: Modifier = Modifier) {
    Text(
        text = displayText,
        style = typography,
        modifier = modifier
    )
}

fun calculateValue(doubleValue: Int, rate: Int, divide: Int): Int {
    // Calculate the result
    return (rate * doubleValue / 100 / divide)
}
