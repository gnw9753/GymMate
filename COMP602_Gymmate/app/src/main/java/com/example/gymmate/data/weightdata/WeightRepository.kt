package com.example.gymmate.data.weightdata

import kotlinx.coroutines.flow.Flow

interface WeightRepository {
    fun getAllWeightFromEmail(email: String): Flow<List<WeightEntity>>
    fun deleteAllWeightByEmail(email: String)
    suspend fun insertWeight(weight: WeightEntity)
    suspend fun deleteWeight(weight: WeightEntity)
    suspend fun updateWeight(weight: WeightEntity)
}