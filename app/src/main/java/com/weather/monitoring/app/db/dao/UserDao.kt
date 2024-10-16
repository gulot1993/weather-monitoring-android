package com.weather.monitoring.app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weather.monitoring.app.data.domain.User
import com.weather.monitoring.app.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): Flow<UserEntity>
}