package com.baims.dailyforcast.presentation.screens.model.state

import com.baims.dailyforcast.domain.utils.TemperatureStatus


data class WeatherPresentationModel(
    val dayDate: String = "",
    val temperatureDegrees: String = "",
    val temperatureStatus: TemperatureStatus = TemperatureStatus.CLEAR_SKY,
    val wetBows: String = "",
    val windSpeed: String = "",
    val cloud: String = "",
    val iconUrl: String = "",
    val dayName: String = ""
)


