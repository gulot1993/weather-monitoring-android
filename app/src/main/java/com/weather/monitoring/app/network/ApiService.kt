package com.weather.monitoring.app.network

import com.weather.monitoring.app.data.dto.WeatherForecastDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getCurrentWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): WeatherForecastDTO
}