package com.example.gymmate.caloriespage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gymmate.data.fooddata.FoodConsumptionEntity
import com.example.gymmate.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun QuickAddCard(
    foodList: List<FoodConsumptionEntity>,
    viewModel: CaloriesPageViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        // Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Quick Add",
                style = Typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(5.dp))
            var count = 0
            for (food in foodList) {
                if (count > 2) break
                food?.let {
                    QuickAddRow(viewModel, food = it)
                    count++
                }
            }
        }
    }
}

@Composable
fun QuickAddRow(
    viewModel: CaloriesPageViewModel,
    food: FoodConsumptionEntity,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(5.dp)
        ) {
            Text(
                text = food.foodName,
                style = Typography.titleLarge,
                textAlign = TextAlign.Start,
            )
            Text(
                "${food.calories}cal | ${food.fat} fat | ${food.protein} protein | ${food.carbs} carbs",
            )

        }
        Spacer(modifier = Modifier.weight(0.05f))
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.addFoodButton(food)
                }
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(5.dp)
                .weight(0.25f)
        ) {
            Text(text = "Add")
        }
    }
}