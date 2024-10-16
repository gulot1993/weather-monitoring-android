package com.weather.monitoring.app.data.domain

import com.weather.monitoring.app.data.domain.WeatherForecast.Companion.WeatherConditionTime.CLOUDY_DAY
import com.weather.monitoring.app.data.domain.WeatherForecast.Companion.WeatherConditionTime.CLOUDY_NIGHT
import com.weather.monitoring.app.data.domain.WeatherForecast.Companion.WeatherConditionTime.RAINY_DAY
import com.weather.monitoring.app.data.domain.WeatherForecast.Companion.WeatherConditionTime.RAINY_NIGHT

data class WeatherForecast(
    val temperature: Double = 0.00,
    val location: String = "",
    val sunrise: String = "",
    val sunset: String = "",
    val condition: WeatherConditionTime = RAINY_DAY,
    val description: String = ""
) {
    companion object {
        enum class WeatherCondition(val condition: String) {
            RAIN("rain"),
            CLOUDY("clouds"),
            THUNDERSTORM("thunderstorm"),
            CLEAR("clear");
        }

        enum class WeatherConditionTime {
            RAINY_DAY,
            RAINY_NIGHT,
            CLOUDY_DAY,
            CLOUDY_NIGHT;
        }

        fun evaluateWeatherConditionTime(isDay: Boolean, condition: WeatherCondition): WeatherConditionTime {
            return if (isDay) {
                if (isRaining(condition)) {
                    RAINY_DAY
                } else {
                    CLOUDY_DAY
                }
            } else {
                if (isRaining(condition)) {
                    RAINY_NIGHT
                } else {
                    CLOUDY_NIGHT
                }
            }
        }

        private fun isRaining(condition: WeatherCondition): Boolean {
            return condition == WeatherCondition.THUNDERSTORM || condition == WeatherCondition.RAIN
        }
    }
}