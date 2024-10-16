package com.weather.monitoring.app.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherMainDTO(
    val temp: Double
) : Parcelable