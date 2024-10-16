package com.weather.monitoring.app.di

import com.weather.monitoring.app.repository.WeatherRepository
import com.weather.monitoring.app.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun providesWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}