package com.weather.monitoring.app.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val completeName: String
) : Parcelable
