package com.weather.monitoring.app.features.home

import com.weather.monitoring.app.data.domain.WeatherForecast

data class HomeUIState(
    val isLoading: Boolean = false,
    val error: String = "",
    val weather: WeatherForecast = WeatherForecast()
)
