package com.baims.dailyforcast.presentation.screens.mapper

import com.baims.dailyforcast.domain.model.*
import com.baims.dailyforcast.domain.utils.TemperatureStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherPresentationMapperKtTest {

    @Test
    fun `test WeatherResponseModel to WeatherPresentationListModel mapping`() {
        val weatherItemModel = WeatherItemModel(
            dt = 123456789,
            main = MainModel(293.15, 290.0, 292.0, 294.0, 1010, 1015, 1000, 75, 0.1),
            weather = listOf(WeatherModel(1, "Clear", "clear sky", "01d")),
            clouds = CloudsModel(10),
            wind = WindModel(10.0, 180, 5.0),
            visibility = 10000,
            pop = 0.1,
            rain = RainModel(0.5),
            sys = SysModel("pod"),
            dtTxt = "2024-09-06 12:00:00"
        )
        val weatherResponseModel = WeatherResponseModel(
            cod = "200",
            message = 0,
            cnt = 1,
            list = listOf(weatherItemModel),
            city = CityResponseModel(
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

        val result = weatherResponseModel.toWeatherPresentationListModel()

        assertEquals(1, result?.size)
        val presentationModel = result?.first()
        assertEquals("clear sky", presentationModel?.temperatureStatus?.status)
        assertEquals("Fri", presentationModel?.dayName)  // Adjusted for the date in the example
        assertEquals("20°", presentationModel?.temperatureDegrees)
    }

    @Test
    fun `test WeatherItemModel to WeatherPresentationModel mapping`() {
        val weatherItemModel = WeatherItemModel(
            dt = 123456789,
            main = MainModel(293.15, 290.0, 292.0, 294.0, 1010, 1015, 1000, 75, 0.1),
            weather = listOf(WeatherModel(1, "Clear", "clear sky", "01d")),
            clouds = CloudsModel(10),
            wind = WindModel(10.0, 180, 5.0),
            visibility = 10000,
            pop = 0.1,
            rain = RainModel(0.5),
            sys = SysModel("pod"),
            dtTxt = "2024-09-06 12:00:00"
        )

        val result = weatherItemModel.toWeatherPresentationModel()

        assertEquals("clear sky", result.temperatureStatus.status)
        assertEquals("Fri", result.dayName)  // Assuming the date is 2024-09-06 (a Saturday)
        assertEquals("20°", result.temperatureDegrees)
        assertEquals("75 %", result.wetBows)
        assertEquals("10.0 KM/H", result.windSpeed)
        assertEquals("10 %", result.cloud)
        assertEquals("https://openweathermap.org/img/wn/01d@4x.png", result.iconUrl)
    }

    @Test
    fun `test WeatherModel to TemperatureStatus mapping`() {
        val weatherModel = WeatherModel(1, "Clear", "clear sky", "01d")
        val result = weatherModel.toTemperatureStatus()
        assertEquals(TemperatureStatus.CLEAR_SKY, result)

        val rainyWeatherModel = WeatherModel(1, "Rain", "rain", "01d")
        val rainyResult = rainyWeatherModel.toTemperatureStatus()
        assertEquals(TemperatureStatus.RAIN, rainyResult)

        val unknownWeatherModel = WeatherModel(1, "Unknown", "unknown", "01d")
        val unknownResult = unknownWeatherModel.toTemperatureStatus()
        assertEquals(TemperatureStatus.NON, unknownResult)
    }

    @Test
    fun `test getDayOfWeekName utility method`() {
        val dateString = "2024-09-06 12:00:00"
        val result = getDayOfWeekName(dateString)
        assertEquals("Fri", result)
    }

}
