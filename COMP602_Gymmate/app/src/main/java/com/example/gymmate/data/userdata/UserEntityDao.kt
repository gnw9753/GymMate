package com.example.gymmate.data.userdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEntityDao {

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): Flow<UserEntity>

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUserById(id: Int)

    @Query("DELETE FROM users WHERE email = :email")
    fun deleteUserByEmail(email: String)

    @Query("DELETE FROM users WHERE email = :email AND id = :id")
    fun deleteUserByEmailAndId(email: String, id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

}