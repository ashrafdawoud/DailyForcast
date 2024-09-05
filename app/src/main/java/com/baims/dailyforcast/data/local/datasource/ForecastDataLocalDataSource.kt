package com.baims.dailyforcast.data.local.datasource

import com.baims.dailyforcast.data.local.entity.WeatherResponseEntity

interface ForecastDataLocalDataSource {
    suspend fun insertWeatherTable(weatherEntity: WeatherResponseEntity)
    suspend fun getWeatherByCity(cityId: Int): List<WeatherResponseEntity>
    suspend fun deleteAllWeatherResponses()
}