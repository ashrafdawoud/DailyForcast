package com.baims.dailyforcast.data.local.datasource

import com.baims.dailyforcast.data.local.dao.WeatherDao
import com.baims.dailyforcast.data.local.entity.WeatherResponseEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForecastDataLocalDataSourceImpTest {
    @Mock
    private lateinit var weatherDao: WeatherDao
    private lateinit var forecastDataLocalDataSource: ForecastDataLocalDataSourceImp

    @Before
    fun setup() {
        forecastDataLocalDataSource = ForecastDataLocalDataSourceImp(weatherDao)
    }

    @Test
    fun `test insertWeatherTable calls weatherDao insert`() = runBlocking {
        val weatherResponseEntity = WeatherResponseEntity(
            cod = "200",
            cityId = 1,
            message = 0,
            cnt = 1,
            list = emptyList(),
            city = null
        )
        forecastDataLocalDataSource.insertWeatherTable(weatherResponseEntity)

        verify(weatherDao).insertWeatherTable(weatherResponseEntity)
    }

    @Test
    fun `test getWeatherByCity retrieves weather data for cityId`() = runBlocking {
        val cityId = 1
        val weatherResponseEntity = WeatherResponseEntity(
            cod = "200",
            cityId = cityId,
            message = 0,
            cnt = 1,
            list = emptyList(),
            city = null
        )
        `when`(weatherDao.getAllWeatherResponses(cityId)).thenReturn(listOf(weatherResponseEntity))

        val result = forecastDataLocalDataSource.getWeatherByCity(cityId)

        assertEquals(1, result.size)
        assertEquals(weatherResponseEntity, result.first())
    }

    @Test
    fun `test deleteAllWeatherResponses calls weatherDao delete`() = runBlocking {
        forecastDataLocalDataSource.deleteAllWeatherResponses()

        verify(weatherDao).deleteAllWeatherResponses()
    }
}