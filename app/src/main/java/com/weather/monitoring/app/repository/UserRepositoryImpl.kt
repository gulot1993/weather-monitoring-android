package com.weather.monitoring.app.repository

import com.weather.monitoring.app.data.entity.UserEntity
import com.weather.monitoring.app.db.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : UserRepository {
    override suspend fun registerUser(user: UserEntity) {
        appDatabase.userDao().insertUsers(user)
    }

    override suspend fun getUser(username: String, password: String): Flow<UserEntity> {
        return appDatabase.userDao().getUser(username, password)
    }
}