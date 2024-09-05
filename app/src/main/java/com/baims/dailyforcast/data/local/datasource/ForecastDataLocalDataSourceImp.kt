package com.baims.dailyforcast.data.local.datasource

import com.baims.dailyforcast.data.local.dao.WeatherDao
import com.baims.dailyforcast.data.local.entity.WeatherResponseEntity

class ForecastDataLocalDataSourceImp(
    private val weatherDao: WeatherDao
) : ForecastDataLocalDataSource {
    override suspend fun insertWeatherTable(weatherEntity: WeatherResponseEntity) =
        weatherDao.insertWeatherTable(weatherEntity)

    override suspend fun getWeatherByCity(cityId: Int): List<WeatherResponseEntity> =
        weatherDao.getAllWeatherResponses(cityId)

    override suspend fun deleteAllWeatherResponses() =
        weatherDao.deleteAllWeatherResponses()
}