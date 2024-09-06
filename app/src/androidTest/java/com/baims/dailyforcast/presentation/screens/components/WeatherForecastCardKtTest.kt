package com.baims.dailyforcast.presentation.screens.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.baims.dailyforcast.domain.utils.TemperatureStatus
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel
import org.junit.Rule
import org.junit.Test

class WeatherForecastCardKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeWeatherData = WeatherPresentationModel(
        dayName = "Monday",
        iconUrl = "https://example.com/icon.png",
        temperatureStatus = TemperatureStatus.FEW_CLOUDS,
        temperatureDegrees = "25°C"
    )

    @Test
    fun test_card_displays_correct_data() {
        composeTestRule.setContent {
            WeatherForecastCard(fakeWeatherData)
        }

        composeTestRule.onNodeWithText(fakeWeatherData.dayName)


        composeTestRule.onNodeWithText(fakeWeatherData.temperatureStatus.status)

        composeTestRule.onNodeWithText(fakeWeatherData.temperatureDegrees)
    }
}