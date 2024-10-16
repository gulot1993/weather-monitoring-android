package com.weather.monitoring.app.data.dto

import android.os.Parcelable
import com.weather.monitoring.app.data.domain.WeatherForecast
import com.weather.monitoring.app.data.entity.WeatherForecastEntity
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
    val sys: WeatherSysDTO,
    val id: Long,
    val weatherId: Long
) : Parcelable {
    companion object {
        fun WeatherForecastDTO.toEntity(): WeatherForecastEntity {
            return with(this) {
                WeatherForecastEntity(
                    temperature = main.temp,
                    sunset = sys.sunset,
                    sunrise = sys.sunrise,
                    location = listOf(name, sys.country).joinToString(","),
                    condition = weather[0].main,
                    description = weather[0].description,
                    dt = dt,
                    weatherId = weatherId
                )
            }
        }
    }
}
