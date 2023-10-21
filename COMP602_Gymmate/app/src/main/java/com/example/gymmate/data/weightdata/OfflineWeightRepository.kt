package com.example.gymmate.data.weightdata

import kotlinx.coroutines.flow.Flow

class OfflineWeightRepository(private val weightDao: WeightDao): WeightRepository {
    override fun getAllWeightFromEmail(email: String): Flow<List<WeightEntity>> = weightDao.getAllWeightFromEmail(email)

    override fun deleteAllWeightByEmail(email: String) = weightDao.deleteAllFoodByEmail(email)

    override suspend fun insertWeight(weight: WeightEntity) = weightDao.insert(weight)

    override suspend fun deleteWeight(weight: WeightEntity) = weightDao.delete(weight)

    override suspend fun updateWeight(weight: WeightEntity) = weightDao.update(weight)
}