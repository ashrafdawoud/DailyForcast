package com.baims.dailyforcast.di

import android.content.Context
import androidx.room.Room
import com.baims.dailyforcast.data.local.dao.WeatherDao
import com.baims.dailyforcast.data.local.db.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(context, RoomDatabase::class.java, RoomDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideWeatherDao(roomDatabase: RoomDatabase): WeatherDao{
        return roomDatabase.weatherDao()
    }
}