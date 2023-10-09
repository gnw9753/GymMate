package com.example.gymmate.data.logindata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gymmate.data.userdata.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginEntityDao {

    @Query("SELECT * FROM user_login WHERE email = :email")
    fun getUserLoginByEmail(email:String): Flow<LoginEntity>

    @Query("DELETE FROM user_login WHERE email = :email")
    fun deleteUserLoginByEmail(email:String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(loginEntity: LoginEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(loginEntity: LoginEntity)

    @Delete
    suspend fun delete(loginEntity: LoginEntity)

}