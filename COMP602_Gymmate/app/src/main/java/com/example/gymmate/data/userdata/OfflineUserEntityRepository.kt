package com.example.gymmate.data.userdata

import kotlinx.coroutines.flow.Flow

class OfflineUserEntityRepository(private val userEntityDao: UserEntityDao): UserEntityRepository {

    override fun getUserByEmail(email: String): Flow<UserEntity> = userEntityDao.getUserByEmail(email)

    override fun deleteUserById(id: Int) = userEntityDao.deleteUserById(id)

    override fun deleteUserByEmail(email: String) = userEntityDao.deleteUserByEmail(email)

    override fun deleteUserByEmailAndId(email: String, id: Int) = userEntityDao.deleteUserByEmailAndId(email, id)

    override suspend fun insertUser(user: UserEntity) = userEntityDao.insert(user)

    override suspend fun deleteUser(user: UserEntity) = userEntityDao.delete(user)

    override suspend fun updateUser(user: UserEntity) = userEntityDao.update(user)
}