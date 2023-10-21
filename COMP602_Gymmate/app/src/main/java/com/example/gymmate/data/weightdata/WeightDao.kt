package com.example.gymmate.data.weightdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface WeightDao {

    @Query("SELECT * FROM user_weight WHERE email = :email")
    fun getAllWeightFromEmail(email: String): Flow<List<WeightEntity>>

    @Query("DELETE FROM user_weight WHERE email = :email")
    fun deleteAllFoodByEmail(email:String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weight: WeightEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(weight: WeightEntity)
    @Delete
    suspend fun delete(weight: WeightEntity)


}