package com.weather.monitoring.app.data.dto

import android.os.Parcelable
import com.weather.monitoring.app.data.domain.WeatherForecast
import com.weather.monitoring.app.utils.Constants
import com.weather.monitoring.app.utils.Extensions
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import timber.log.Timber

@Parcelize
data class WeatherForecastDTO(
    val weather: List<WeatherDTO>,
    val name: String,
    val dt: Long,
    val main: WeatherMainDTO,
    val sys: WeatherSysDTO
) : Parcelable {
    companion object {
        fun WeatherForecastDTO.toDomain(): WeatherForecast {
            val weatherCondition = WeatherForecast.Companion.WeatherCondition.entries.find { it.condition.equals(weather[0].main, ignoreCase = true) } ?: WeatherForecast.Companion.WeatherCondition.CLOUDY
            val isDayTime = DateTime(dt * 1000).toDateTime(DateTimeZone.getDefault()).toString(Constants.TIME_FORMATTER).contains("AM", ignoreCase = true)
            return with(this) {
                WeatherForecast(
                    temperature = Extensions.kelvinToCelsiusConversion(main.temp),
                    sunrise = DateTime(sys.sunrise * 1000).toDateTime(DateTimeZone.getDefault()).toString(Constants.TIME_FORMATTER),
                    sunset = DateTime(sys.sunset * 1000).toDateTime(DateTimeZone.getDefault()).toString(Constants.TIME_FORMATTER),
                    location = listOf(name, sys.country).joinToString(", "),
                    condition = WeatherForecast.evaluateWeatherConditionTime(isDayTime, weatherCondition),
                    description = weather[0].description
                )
            }
        }
    }
}
