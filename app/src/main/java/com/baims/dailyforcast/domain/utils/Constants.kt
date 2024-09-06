package com.baims.dailyforcast.domain.utils

enum class TemperatureStatus(val status: String) {
    CLEAR_SKY("clear sky"),
    FEW_CLOUDS("few clouds"),
    SCATTERED_CLOUDS("scattered clouds"),
    BROKEN_CLOUDS("broken clouds"),
    SHOWER_RAIN("shower rain"),
    RAIN("rain"),
    MIST("mist"),
    SNOWY("snow"),
    THUNDER_STORM("thunderstorm"),
    NON("non")
}