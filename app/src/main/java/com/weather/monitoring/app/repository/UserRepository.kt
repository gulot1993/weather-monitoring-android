package com.weather.monitoring.app.repository

import com.weather.monitoring.app.data.domain.User
import com.weather.monitoring.app.data.entity.UserEntity

interface UserRepository {
    suspend fun registerUser(user: UserEntity)
    suspend fun getUser(username: String, password: String): User?
}