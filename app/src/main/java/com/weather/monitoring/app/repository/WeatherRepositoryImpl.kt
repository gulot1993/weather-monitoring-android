package com.weather.monitoring.app.repository

import com.weather.monitoring.app.BuildConfig
import com.weather.monitoring.app.base.ResourceState.*
import com.weather.monitoring.app.network.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): WeatherRepository {
    override suspend fun getWeatherForecast(lat: Double, lon: Double) = flow {
        emit(Loading())
        val response = apiService.getCurrentWeatherForecast(lat, lon, BuildConfig.API_KEY)
        emit(Success(data = response))
    }.catch {
        emit(Error(message = it.message.toString()))
    }
}