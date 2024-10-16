package com.weather.monitoring.app.repository

import com.weather.monitoring.app.data.domain.User
import com.weather.monitoring.app.data.entity.UserEntity
import com.weather.monitoring.app.data.entity.UserEntity.Companion.toDomain
import com.weather.monitoring.app.db.AppDatabase
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : UserRepository {
    override suspend fun registerUser(user: UserEntity) {
        appDatabase.userDao().insertUsers(user)
    }

    override suspend fun getUser(username: String, password: String): User? {
        return appDatabase.userDao().getUser(username, password)?.toDomain()
    }
}