package com.baims.dailyforcast.di

import com.baims.dailyforcast.data.local.dao.WeatherDao
import com.baims.dailyforcast.data.local.datasource.ForecastDataLocalDataSource
import com.baims.dailyforcast.data.local.datasource.ForecastDataLocalDataSourceImp
import com.baims.dailyforcast.data.remote.api.ForecastApi
import com.baims.dailyforcast.data.remote.datasource.ForecastDataRemoteDataSource
import com.baims.dailyforcast.data.remote.datasource.ForecastDataRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideForecastDataRemoteDataSource(forecastApi: ForecastApi): ForecastDataRemoteDataSource =
        ForecastDataRemoteDataSourceImp(forecastApi)

    @Singleton
    @Provides
    fun provideForecastDataLocalDataSource(weatherDao: WeatherDao): ForecastDataLocalDataSource =
        ForecastDataLocalDataSourceImp(weatherDao)
}