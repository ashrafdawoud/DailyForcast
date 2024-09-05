package com.baims.dailyforcast.di

import com.baims.dailyforcast.data.local.datasource.ForecastDataLocalDataSource
import com.baims.dailyforcast.data.remote.datasource.ForecastDataRemoteDataSource
import com.baims.dailyforcast.data.repository.ForecastRepositoryImp
import com.baims.dailyforcast.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideForecastRepository(
        forecastDataRemoteDataSource: ForecastDataRemoteDataSource,
        forecastDataLocalDataSource: ForecastDataLocalDataSource
    ): ForecastRepository =
        ForecastRepositoryImp(
            forecastDataRemoteDataSource = forecastDataRemoteDataSource,
            forecastDataLocalDataSource = forecastDataLocalDataSource
        )
}