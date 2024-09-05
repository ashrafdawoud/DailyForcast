package com.baims.dailyforcast.data.remote.datasource

import com.baims.dailyforcast.data.remote.api.ForecastApi
import com.baims.dailyforcast.data.remote.dto.WeatherResponseDto
import retrofit2.Response

class ForecastDataRemoteDataSourceImp(
    private val forecastApi: ForecastApi
) : ForecastDataRemoteDataSource {
    override suspend fun getForecastData(lat: Double, lon: Double): Response<WeatherResponseDto> =
        forecastApi.getForecastData(lat = lat, lon = lon)
}