package com.example.gymmate.caloriespage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.data.fooddata.FoodConsumptionEntity
import com.example.gymmate.ui.theme.Typography


@Composable
fun SearchFoodPage(
    viewModel: CaloriesPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    viewModel.loadCSV((context))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(viewModel)

        TextField(
            value = viewModel.searchFoodText,
            onValueChange = {
                viewModel.searchFoodText = it
                viewModel.searchFood()
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("Search") },
        )

        DisplayFoodItem(viewModel, modifier.padding(5.dp))
        SearchFoodBottomSheet(viewModel)

    }
}

@Composable
fun TopBar(
    viewModel: CaloriesPageViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = {
                viewModel.displayAddFood = false
            },
            modifier = Modifier
                .weight(0.125f)

        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
            )
        }
        Spacer(modifier = Modifier.weight(0.125f))
        Text(
            text = "Add Food",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(Icons.Default.ArrowBack.defaultHeight * 2)
                .weight(0.5f)
                .padding(10.dp)
        )
        Spacer(
            modifier = Modifier
                .weight(0.25f)
                .background(Color.Black)
        )
    }
}

@Composable
fun DisplayFoodItem(
    viewModel: CaloriesPageViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(5.dp)
    ) {
        LazyColumn(modifier = modifier) {
            items(viewModel.searchFoodListResult) { foodItem ->
                FoodItem(viewModel = viewModel, foodConsumptionEntity = foodItem)
            }
        }
    }
}

@Composable
fun FoodItem(
    viewModel: CaloriesPageViewModel,
    foodConsumptionEntity: FoodConsumptionEntity,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column {
                val calories = (foodConsumptionEntity.calories / foodConsumptionEntity.grams) * 100
                val formattedCalories = String.format("%.2f Cal", calories)

                Text(
                    text = foodConsumptionEntity.foodName,
                    style = Typography.titleMedium,
                )
                Text(formattedCalories)
            }

            Spacer(Modifier.weight(1f))
            Button(onClick = {
                viewModel.foodItem = foodConsumptionEntity
                viewModel.foodItemCalories = foodConsumptionEntity.calories
                viewModel.foodItemCarb = foodConsumptionEntity.carbs
                viewModel.foodItemFat = foodConsumptionEntity.fat
                viewModel.foodItemProtein = foodConsumptionEntity.protein
                viewModel.foodItemGram = foodConsumptionEntity.grams
                viewModel.openBottomSheet = !viewModel.openBottomSheet
            }) {
                Text("Add")
            }
        }
    }

}

