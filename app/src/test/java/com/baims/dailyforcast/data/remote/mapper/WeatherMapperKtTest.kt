package com.baims.dailyforcast.data.remote.mapper

import com.baims.dailyforcast.data.remote.dto.*
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherMapperKtTest {
    @Test
    fun `test WeatherResponseDto to WeatherResponseModel mapping`() {
        val dto = WeatherResponseDto(
            cod = "200",
            message = 0,
            cnt = 1,
            list = listOf(WeatherItemDto(12345)),
            city = CityDto(1, "City", CordDto(12.34, 56.78), "Country", 1000, 3600, 1000L, 1000L)
        )
        val result = dto.toWeatherResponseModel()
        assertEquals(dto.cod, result.cod)
        assertEquals(dto.message, result.message)
        assertEquals(dto.city?.id, result.city?.id)
    }

    @Test
    fun `test WeatherItemDto to WeatherItemModel mapping`() {
        val dto = WeatherItemDto(
            dt = 12345,
            main = MainDto(15.0, 10.0, 5.0, 20.0, 1010, 1015, 1000, 75, 0.1),
            weather = listOf(WeatherDto(1, "Clear", "clear sky", "01d")),
            clouds = CloudsDto(0),
            wind = WindDto(5.0, 180, 10.0),
            visibility = 10000,
            pop = 0.1,
            rain = RainDto(1.0),
            sys = SysDto("pod"),
            dtTxt = "2024-09-06 12:00:00"
        )
        val result = dto.toWeatherItemModel()
        assertEquals(dto.dt, result.dt)
        assertEquals(dto.main?.temp, result.main?.temp)
        assertEquals(dto.weather?.first()?.id, result.weather?.first()?.id)
    }

    @Test
    fun `test CityDto to CityResponseModel mapping`() {
        val dto = CityDto(
            id = 1,
            name = "City",
            cord = CordDto(12.34, 56.78),
            country = "Country",
            population = 1000,
            timezone = 3600,
            sunrise = 1000L,
            sunset = 1000L
        )
        val result = dto.toCityModel()
        assertEquals(dto.id, result.id)
        assertEquals(dto.name, result.name)
        assertEquals(dto.cord?.lat, result.cord?.lat)
    }

    @Test
    fun `test CordDto to CordModel mapping`() {
        val dto = CordDto(12.34, 56.78)
        val result = dto.toModel()
        assertEquals(dto.lat, result.lat)
        assertEquals(dto.lon, result.lon)
    }

    @Test
    fun `test CloudsDto to CloudsModel mapping`() {
        val dto = CloudsDto(100)
        val result = dto.toCloudsModel()
        assertEquals(dto.all, result.all)
    }

    @Test
    fun `test WindDto to WindModel mapping`() {
        val dto = WindDto(10.0, 180, 5.0)
        val result = dto.toWindModel()
        assertEquals(dto.speed, result.speed)
        assertEquals(dto.deg, result.deg)
        assertEquals(dto.gust, result.gust)
    }

    @Test
    fun `test RainDto to RainModel mapping`() {
        val dto = RainDto(3.0)
        val result = dto.toRainModel()
        assertEquals(dto.threeHours, result.threeHours)
    }

    @Test
    fun `test SysDto to SysModel mapping`() {
        val dto = SysDto("pod")
        val result = dto.toSysModel()
        assertEquals(dto.pod, result.pod)
    }

    @Test
    fun `test WeatherDto to WeatherModel mapping`() {
        val dto = WeatherDto(1, "Clear", "clear sky", "01d")
        val result = dto.toWeatherModel()
        assertEquals(dto.id, result.id)
        assertEquals(dto.main, result.main)
        assertEquals(dto.description, result.description)
        assertEquals(dto.icon, result.icon)
    }

    @Test
    fun `test MainDto to MainModel mapping`() {
        val dto = MainDto(15.0, 10.0, 5.0, 20.0, 1010, 1015, 1000, 75, 0.1)
        val result = dto.toWeatherModel()
        assertEquals(dto.temp, result.temp)
        assertEquals(dto.feelsLike, result.feelsLike)
        assertEquals(dto.tempMin, result.tempMin)
        assertEquals(dto.tempMax, result.tempMax)
    }
}