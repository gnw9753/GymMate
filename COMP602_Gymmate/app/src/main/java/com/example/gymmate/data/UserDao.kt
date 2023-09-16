package com.example.gymmate.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE name = :name")
    fun getItem(name: String): Flow<User>
}