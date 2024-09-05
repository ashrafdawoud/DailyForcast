package com.baims.dailyforcast.data.remote.api

import com.baims.dailyforcast.BuildConfig
import com.baims.dailyforcast.data.remote.dto.WeatherResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("forecast")
    suspend fun getForecastData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.FORECAST_API_KEY
    ):Response<WeatherResponseDto>
}