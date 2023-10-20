package com.example.gymmate.data.fooddata

import kotlinx.coroutines.flow.Flow

interface FoodConsumptionRepository {

    fun getAllFoodFromEmail(email: String): Flow<List<FoodConsumptionEntity>>

    fun deleteAllFoodByEmail(email: String)

    suspend fun insertFood(foodConsumptionEntity: FoodConsumptionEntity)

    suspend fun deleteFood(foodConsumptionEntity: FoodConsumptionEntity)

    suspend fun updateFood(foodConsumptionEntity: FoodConsumptionEntity)

}