package com.weather.monitoring.app.data.domain

import android.os.Parcelable
import com.weather.monitoring.app.data.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String
) : Parcelable {
    companion object {
        fun User.toEntity(): UserEntity {
            return with(this) {
                UserEntity(
                    username = username,
                    password = password,
                    firstname = firstname,
                    lastname = lastname
                )
            }
        }
    }
}
