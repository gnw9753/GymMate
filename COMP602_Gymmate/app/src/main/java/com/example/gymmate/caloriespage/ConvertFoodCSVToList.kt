package com.example.gymmate.caloriespage

import com.example.gymmate.data.fooddata.FoodConsumptionEntity

class ConvertFoodCSVToList(val email: String, val foodCSV: List<List<String>>) {

    fun convertToFoodList(): List<FoodConsumptionEntity> {
        var foodList: MutableList<FoodConsumptionEntity> = mutableListOf()
        for(foodItem in foodCSV) {
            var tempFoodItem = FoodConsumptionEntity(
                email = email,
                foodName = foodItem[0],
                grams = foodItem[1].toFloat(),
                calories = foodItem[2].toFloat(),
                protein = foodItem[3].toFloat(),
                fat = foodItem[4].toFloat(),
                carbs = foodItem[5].toFloat(),
            )
            foodList.add(tempFoodItem)
        }
        return foodList
    }
}