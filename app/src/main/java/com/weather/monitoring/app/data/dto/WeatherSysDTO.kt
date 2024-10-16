package com.weather.monitoring.app.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherSysDTO(
    val country: String,
    val sunrise: Long,
    val sunset: Long
) : Parcelable
