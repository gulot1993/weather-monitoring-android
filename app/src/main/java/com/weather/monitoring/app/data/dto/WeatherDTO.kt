package com.weather.monitoring.app.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDTO(
    val main: String,
    val description: String
) : Parcelable
