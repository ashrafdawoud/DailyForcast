package com.baims.dailyforcast.domain.model

data class CityModel(
    val id: Int = 0,
    val cityNameAr: String = "",
    val cityNameEn: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0
)