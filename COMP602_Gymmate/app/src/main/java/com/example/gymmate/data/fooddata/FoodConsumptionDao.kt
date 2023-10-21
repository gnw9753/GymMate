package com.example.gymmate.data.fooddata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodConsumptionDao {

    @Query("SELECT * FROM food_consumption WHERE email = :email")
    fun getAllFoodFromEmail(email: String): Flow<List<FoodConsumptionEntity>>

    @Query("DELETE FROM food_consumption WHERE email = :email")
    fun deleteAllFoodByEmail(email: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodConsumptionEntity: FoodConsumptionEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(foodConsumptionEntity: FoodConsumptionEntity)

    @Delete
    suspend fun delete(foodConsumptionEntity: FoodConsumptionEntity)
}