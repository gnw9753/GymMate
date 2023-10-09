package com.example.gymmate.data.logindata

import kotlinx.coroutines.flow.Flow

class OfflineLoginEntityRepository(private val loginEntityDao: LoginEntityDao): LoginEntityRepository {
    override fun getUserLoginByEmail(email: String): Flow<LoginEntity> = loginEntityDao.getUserLoginByEmail(email)

    override fun deleteUserLoginByEmail(email: String) = loginEntityDao.deleteUserLoginByEmail(email)

    override suspend fun insertUserLogin(loginEntity: LoginEntity) = loginEntityDao.insert(loginEntity)

    override suspend fun deleteUserLogin(loginEntity: LoginEntity) = loginEntityDao.delete(loginEntity)

    override suspend fun updateUserLogin(loginEntity: LoginEntity) = loginEntityDao.update(loginEntity)
}