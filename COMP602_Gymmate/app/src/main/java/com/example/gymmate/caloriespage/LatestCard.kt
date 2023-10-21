package com.example.gymmate.caloriespage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gymmate.data.fooddata.FoodConsumptionEntity
import com.example.gymmate.ui.theme.Typography

@Composable
fun LatestCard(
    foodList: List<FoodConsumptionEntity>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Title
            Text(
                text = "Latest",
                style = Typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()

            )
            Divider(modifier = Modifier.padding(5.dp))
            LazyColumn(modifier = modifier) {
                items(foodList) { foodItem ->
                    DisplayLatestFood(foodList = foodItem)
                }
            }
        }
    }
}

@Composable
fun DisplayLatestFood(
    foodList: FoodConsumptionEntity,
    modifier: Modifier = Modifier
) {
    Row() {
        foodList?.let {
            Column() {
                DisplayText(
                    displayText = " • " + it.foodName,
                    typography = Typography.displaySmall,
                    modifier.padding(3.dp)
                )
                DisplayText(
                    displayText = "     ${foodList.calories}cal | ${foodList.fat} fat | ${foodList.protein} protein | ${foodList.carbs} carbs",
                    typography = Typography.bodyMedium
                )
            }
        }
    }
}