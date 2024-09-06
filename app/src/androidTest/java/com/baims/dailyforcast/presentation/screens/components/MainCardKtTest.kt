package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.utils.TemperatureStatus
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainCardKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    private val weatherData = WeatherPresentationModel(
        temperatureDegrees = "25°",
        temperatureStatus = TemperatureStatus.CLEAR_SKY
    )

    private val cities = arrayOf(
        CityModel(id = 1, cityNameAr = "Cairo"),
        CityModel(id = 2, cityNameAr = "London")
    )

    @Test
    fun test_shows_weather_data() {
        composeTestRule.setContent {
            MainCard(weatherData, PaddingValues(0.dp), cities) {}
        }

        composeTestRule.onNodeWithText(weatherData.temperatureDegrees).assertIsDisplayed()
    }

    @Test
    fun test_shows_dropdown_list_with_cities() {
        composeTestRule.setContent {
            MainCard(weatherData, PaddingValues(0.dp), cities) {}
        }
        composeTestRule.onNodeWithText(cities[0].cityNameEn).assertIsDisplayed()
        composeTestRule.onNodeWithText(cities[1].cityNameEn).assertIsDisplayed()
    }
}
