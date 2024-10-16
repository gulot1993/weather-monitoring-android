package com.weather.monitoring.app.repository

import com.weather.monitoring.app.base.ResourceState
import com.weather.monitoring.app.data.domain.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecast(lat: Double, lon: Double): Flow<ResourceState<WeatherForecast>>
    suspend fun getAllWeatherForecasts(): Flow<ResourceState<List<WeatherForecast>>>
}