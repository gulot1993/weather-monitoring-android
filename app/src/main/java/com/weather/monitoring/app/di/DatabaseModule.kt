package com.weather.monitoring.app.di

import android.content.Context
import android.content.SharedPreferences
import com.weather.monitoring.app.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context = context)

    @Provides
    @Singleton
    fun providesPreferenceHelper(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
}