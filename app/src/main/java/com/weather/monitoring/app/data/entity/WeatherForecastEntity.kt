package com.weather.monitoring.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weather.monitoring.app.data.domain.WeatherForecast
import com.weather.monitoring.app.utils.Constants
import com.weather.monitoring.app.utils.Extensions
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

@Entity("weather_forecasts")
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val temperature: Double = 0.00,
    val location: String = "",
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    val condition: String = "",
    val description: String = "",
    val dt: Long = 0L,
    val weatherId: Long
) {
    companion object {
        fun WeatherForecastEntity.toDomain(): WeatherForecast {
            val weatherCondition = WeatherForecast.Companion.WeatherCondition.entries.find { it.condition.equals(condition, ignoreCase = true) } ?: WeatherForecast.Companion.WeatherCondition.CLOUDY
            val dateTime = DateTime(dt * 1000)
                .toDateTime(DateTimeZone.getDefault())
            val hour = dateTime.hourOfDay().get()
            val dateString = dateTime.toString(Constants.DAY_FORMATTER)
            val isDayTime = hour < 18
            return with(this) {
                WeatherForecast(
                    id = id,
                    temperature = Extensions.kelvinToCelsiusConversion(temperature),
                    sunrise = DateTime(sunrise * 1000).toDateTime(DateTimeZone.getDefault()).toString(
                        Constants.TIME_FORMATTER),
                    sunset = DateTime(sunset * 1000).toDateTime(DateTimeZone.getDefault()).toString(Constants.TIME_FORMATTER),
                    location = location,
                    condition = WeatherForecast.evaluateWeatherConditionTime(isDayTime, weatherCondition),
                    description = description,
                    dayOfWeek = dateString
                )
            }
        }
    }
}