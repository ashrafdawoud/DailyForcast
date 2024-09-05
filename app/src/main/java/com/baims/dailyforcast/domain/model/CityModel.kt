package com.baims.dailyforcast.domain.model

data class CityModel (
    val id: Int,
    val cityNameAr: String,
    val cityNameEn: String,
    val lat: Double,
    val lon: Double
)