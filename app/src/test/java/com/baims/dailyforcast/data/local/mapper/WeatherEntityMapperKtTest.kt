package com.baims.dailyforcast.data.local.mapper

import com.baims.dailyforcast.data.local.entity.*
import com.baims.dailyforcast.domain.model.*
import org.junit.Assert.*
import org.junit.Test

class WeatherEntityMapperKtTest {

    @Test
    fun `test WeatherResponseModel to WeatherResponseEntity mapping`() {
        val model = WeatherResponseModel(
            "200",
            0,
            1,
            listOf(WeatherItemModel(12345)),
            CityResponseModel(
                1,
                "City",
                CordModel(12.34, 56.78),
                "Country",
                1000,
                3600,
                1000L,
                1000L
            )
        )
        val expectedCityId = 10
        val result = model.toWeatherResponseEntity(expectedCityId)
        assertEquals(model.cod, result.cod)
        assertEquals(expectedCityId, result.cityId)
        assertEquals(model.message, result.message)
        assertEquals(model.city?.id, result.city?.id)
    }

    @Test
    fun `test WeatherResponseEntity to WeatherResponseModel mapping`() {
        val entity = WeatherResponseEntity(
            200,
            1,
            "0.0",
            1,
            list = listOf(WeatherItemEntity(12345)),
            city = CityEntity(1, "City", CordEntity(12L, 56.78), "Country", 1000, 3600, 1000L, 1000L)
        )
        val result = entity.toWeatherResponseModel()
        assertEquals(entity.cod, result.cod)
        assertEquals(entity.message, result.message)
        assertEquals(entity.city?.id, result.city?.id)
    }

    @Test
    fun `test WeatherItemEntity to WeatherItemModel mapping`() {
        val entity = WeatherItemEntity(
            12345,
            main = MainEntity(15L, 10.0, 5.0, 20.0, 1010.0, 1015, 1000, 75, 1),
            weather = listOf(WeatherEntity(1, "Clear", "clear sky", "01d")),
            clouds = CloudsEntity(0),
            wind = WindEntity(5L, 180.0, 10),
            visibility = 10000,
            pop =0.1,
            rain = RainEntity(1L),
            sys = SysEntity("pod"),
            dtTxt = "2024-09-06 12:00:00"
        )
        val result = entity.toWeatherItemModel()
        assertEquals(entity.dt, result.dt)
        assertEquals(entity.main?.temp, result.main?.temp)
        assertEquals(entity.weather?.first()?.id, result.weather?.first()?.id)
    }

    @Test
    fun `test WeatherItemModel to WeatherItemEntity mapping`() {
        val model = WeatherItemModel(
            12345,
            MainModel(15.0, 10.0, 5.0, 20.0, 1010, 1015, 1000, 75, 0.1),
            listOf(WeatherModel(1, "Clear", "clear sky", "01d")),
            CloudsModel(0),
            WindModel(5.0, 180, 10.0),
            10000,
            0.1,
            RainModel(1.0),
            SysModel("pod"),
            "2024-09-06 12:00:00"
        )
        val result = model.toWeatherItemEntity()
        assertEquals(model.dt, result.dt)
        assertEquals(model.main?.temp, result.main?.temp)
        assertEquals(model.weather?.first()?.id, result.weather?.first()?.id)
    }

    @Test
    fun `test CityEntity to CityResponseModel mapping`() {
        val entity = CityEntity(
            1,
            "City",
            CordEntity(lat = 12.34, lon = 56.78),
            "Country",
            1000,
            3600,
            1000L,
            1000L
        )
        val result = entity.toCityModel()
        assertEquals(entity.id, result.id)
        assertEquals(entity.name, result.name)
        assertEquals(entity.cord?.lat, result.cord?.lat)
    }

    @Test
    fun `test CityResponseModel to CityEntity mapping`() {
        val model = CityResponseModel(
            1,
            "City",
            CordModel(12.34, 56.78),
            "Country",
            1000,
            3600,
            1000L,
            1000L
        )
        val result = model.toCityModel()
        assertEquals(model.id, result.id)
        assertEquals(model.name, result.name)
        assertEquals(model.cord?.lat, result.cord?.lat)
    }

    @Test
    fun `test CordEntity to CordModel mapping`() {
        val entity = CordEntity(lat = 12.34, lon = 56.78)
        val result = entity.toModel()
        assertEquals(entity.lat, result.lat)
        assertEquals(entity.lon, result.lon)
    }

    @Test
    fun `test CordModel to CordEntity mapping`() {
        val model = CordModel(12.34, 56.78)
        val result = model.toModel()
        assertEquals(model.lat, result.lat)
        assertEquals(model.lon, result.lon)
    }

    @Test
    fun `test CloudsEntity to CloudsModel mapping`() {
        val entity = CloudsEntity(100)
        val result = entity.toCloudsModel()
        assertEquals(entity.all, result.all)
    }

    @Test
    fun `test CloudsModel to CloudsEntity mapping`() {
        val model = CloudsModel(100)
        val result = model.toCloudsEntity()
        assertEquals(model.all, result.all)
    }

    @Test
    fun `test WindEntity to WindModel mapping`() {
        val entity = WindEntity(10L, 180.0, 5)
        val result = entity.toWindModel()
        assertEquals(entity.speed, result.speed)
        assertEquals(entity.deg, result.deg)
        assertEquals(entity.gust, result.gust)
    }

    @Test
    fun `test WindModel to WindEntity mapping`() {
        val model = WindModel(10.0, 180, 5.0)
        val result = model.toWindEntity()
        assertEquals(model.speed, result.speed)
        assertEquals(model.deg, result.deg)
        assertEquals(model.gust, result.gust)
    }

    @Test
    fun `test RainEntity to RainModel mapping`() {
        val entity = RainEntity(3L)
        val result = entity.toRainModel()
        assertEquals(entity.threeHours, result.threeHours)
    }

    @Test
    fun `test RainModel to RainEntity mapping`() {
        val model = RainModel(3.0)
        val result = model.toRainEntity()
        assertEquals(model.threeHours, result.threeHours)
    }

    @Test
    fun `test SysEntity to SysModel mapping`() {
        val entity = SysEntity("pod")
        val result = entity.toSysModel()
        assertEquals(entity.pod, result.pod)
    }

    @Test
    fun `test SysModel to SysEntity mapping`() {
        val model = SysModel("pod")
        val result = model.toSysEntity()
        assertEquals(model.pod, result.pod)
    }
}