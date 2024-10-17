package com.weather.monitoring.app.repository

import com.weather.monitoring.app.BuildConfig
import com.weather.monitoring.app.base.ResourceState
import com.weather.monitoring.app.base.ResourceState.Error
import com.weather.monitoring.app.base.ResourceState.Loading
import com.weather.monitoring.app.base.ResourceState.Success
import com.weather.monitoring.app.data.domain.WeatherForecast
import com.weather.monitoring.app.data.dto.WeatherForecastDTO.Companion.toEntity
import com.weather.monitoring.app.data.entity.WeatherForecastEntity.Companion.toDomain
import com.weather.monitoring.app.db.AppDatabase
import com.weather.monitoring.app.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: AppDatabase
): WeatherRepository {
    override suspend fun getWeatherForecast(lat: Double, lon: Double) = flow {
        emit(Loading())
        val response = apiService.getCurrentWeatherForecast(lat, lon, BuildConfig.API_KEY)
        val existingEntity = database.weatherForecastDao().getGetWeatherByDayAndCondition(response.id)
        val entityId = existingEntity?.id?.toLong()
            ?: database.weatherForecastDao().saveWeatherForecast(response.toEntity())
        val domain = database.weatherForecastDao().getWeatherById(entityId).toDomain()
        emit(Success(data = domain))
    }.catch {
        emit(Error(message = it.message.toString()))
    }
    .flowOn(Dispatchers.IO)

    override suspend fun getAllWeatherForecasts() =  database
        .weatherForecastDao()
        .getAllWeatherForecasts()
        .distinctUntilChanged()
        .transform {
            emit(Loading())
            val domain = it.map { it.toDomain() }
            emit(Success(data = domain))
        }.catch {
            emit(Error(message = it.message.toString()))
        }
        .flowOn(Dispatchers.IO)
}