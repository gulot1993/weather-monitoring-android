package com.weather.monitoring.app.repository

import com.weather.monitoring.app.base.ResourceState
import com.weather.monitoring.app.data.dto.WeatherForecastDTO
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecast(lat: Double, lon: Double): Flow<ResourceState<WeatherForecastDTO>>
}