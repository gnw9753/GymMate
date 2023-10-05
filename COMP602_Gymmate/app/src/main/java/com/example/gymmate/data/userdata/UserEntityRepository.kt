package com.example.gymmate.data.userdata

import kotlinx.coroutines.flow.Flow

interface UserEntityRepository {
    // User Dao
    fun getUserByEmail(email: String): Flow<UserEntity>
    fun deleteUserById(id: Int)
    fun deleteUserByEmail(email: String)
    fun deleteUserByEmailAndId(email: String, id: Int)
    suspend fun insertUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
}