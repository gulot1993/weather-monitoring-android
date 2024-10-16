package com.weather.monitoring.app.utils

object Extensions {
    fun kelvinToCelsiusConversion(kelvinTemp: Double): Double {
        val result = kelvinTemp - 273.15
        return Math.round(result * 100.0) / 100.0
    }
}