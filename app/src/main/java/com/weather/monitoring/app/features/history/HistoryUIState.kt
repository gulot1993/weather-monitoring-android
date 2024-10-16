package com.weather.monitoring.app.features.history

import com.weather.monitoring.app.data.domain.WeatherForecast

data class HistoryUIState(
    val isLoading: Boolean = false,
    val weatherForecasts: List<WeatherForecast> = emptyList(),
    val error: String = ""
)
