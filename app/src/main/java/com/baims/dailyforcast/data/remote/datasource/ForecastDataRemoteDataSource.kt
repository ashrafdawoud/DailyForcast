package com.baims.dailyforcast.data.remote.datasource

import com.baims.dailyforcast.data.remote.dto.WeatherResponseDto
import retrofit2.Response

interface ForecastDataRemoteDataSource {
    suspend fun getForecastData(lat: Double, lon: Double): Response<WeatherResponseDto>
}