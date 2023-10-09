package com.example.gymmate.data.logindata

import com.example.gymmate.data.userdata.UserEntity
import kotlinx.coroutines.flow.Flow

interface LoginEntityRepository {
    fun getUserLoginByEmail(email: String): Flow<LoginEntity>
    fun deleteUserLoginByEmail(email: String)
    suspend fun insertUserLogin(loginEntity: LoginEntity)
    suspend fun deleteUserLogin(loginEntity: LoginEntity)
    suspend fun updateUserLogin(loginEntity: LoginEntity)
}