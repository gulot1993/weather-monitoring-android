package com.weather.monitoring.app.repository

import com.weather.monitoring.app.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(user: UserEntity)
    suspend fun getUser(username: String, password: String): Flow<UserEntity>
}