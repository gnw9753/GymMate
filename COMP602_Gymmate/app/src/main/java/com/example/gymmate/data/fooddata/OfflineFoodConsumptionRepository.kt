package com.example.gymmate.data.fooddata

import kotlinx.coroutines.flow.Flow

class OfflineFoodConsumptionRepository(private val foodConsumptionDao: FoodConsumptionDao): FoodConsumptionRepository {
    override fun getAllFoodFromEmail(email: String): Flow<List<FoodConsumptionEntity>> = foodConsumptionDao.getAllFoodFromEmail(email)

    override fun deleteAllFoodByEmail(email: String) = foodConsumptionDao.deleteAllFoodByEmail(email)

    override suspend fun insertFood(foodConsumptionEntity: FoodConsumptionEntity) = foodConsumptionDao.insert(foodConsumptionEntity)

    override suspend fun deleteFood(foodConsumptionEntity: FoodConsumptionEntity) = foodConsumptionDao.delete(foodConsumptionEntity)

    override suspend fun updateFood(foodConsumptionEntity: FoodConsumptionEntity) = foodConsumptionDao.update(foodConsumptionEntity)

}