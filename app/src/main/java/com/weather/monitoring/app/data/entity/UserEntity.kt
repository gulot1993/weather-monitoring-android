package com.weather.monitoring.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weather.monitoring.app.data.domain.User

@Entity("users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val firstname: String,
    val lastname: String,
    val username: String,
    val password: String
) {
    companion object {
        fun UserEntity.toDomain(): User {
            return with(this) {
                val completeName = "$firstname $lastname"
                User(
                    username, password, firstname, lastname
                )
            }
        }
    }
}
