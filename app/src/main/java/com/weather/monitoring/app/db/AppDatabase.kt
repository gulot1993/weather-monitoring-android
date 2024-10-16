package com.weather.monitoring.app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weather.monitoring.app.data.entity.UserEntity
import com.weather.monitoring.app.db.dao.UserDao
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    version = 1,
    entities = [UserEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        const val DB_NAME = "app_db"
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(
                        context = context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                    .build()
                INSTANCE!!
            }
        }
    }
}