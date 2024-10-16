package com.weather.monitoring.app.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weather.monitoring.app.data.entity.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherForecast(entity: WeatherForecastEntity): Long

    @Query("SELECT * FROM weather_forecasts WHERE id = :id")
    suspend fun getWeatherById(id: Long): WeatherForecastEntity

    @Query("SELECT * FROM weather_forecasts")
    fun getAllWeatherForecasts(): Flow<List<WeatherForecastEntity>>

    @Query("SELECT * FROM weather_forecasts WHERE weatherId = :weatherId")
    fun getGetWeatherByDayAndCondition(weatherId: Long): WeatherForecastEntity?
}