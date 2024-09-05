package com.baims.dailyforcast.domain.model

data class WeatherResponseModel(
    val cod: String? = null,
    val message: Int? = null,
    val cnt: Int? = null,
    val list: List<WeatherItemModel>? = null,
    val city: CityResponseModel? = null
)

data class WeatherItemModel(
    val dt: Long? = null,
    val main: MainModel? = null,
    val weather: List<WeatherModel>? = null,
    val clouds: CloudsModel? = null,
    val wind: WindModel? = null,
    val visibility: Int? = null,
    val pop: Double? = null,
    val rain: RainModel? = null,
    val sys: SysModel? = null,
    val dtTxt: String? = null
)

data class MainModel(
    val temp: Double? = null,
    val feelsLike: Double? = null,
    val tempMin: Double? = null,
    val tempMax: Double? = null,
    val pressure: Int? = null,
    val seaLevel: Int? = null,
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val tempKf: Double? = null
)

data class WeatherModel(
    val id: Int? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)

data class CloudsModel(
    val all: Int? = null
)

data class WindModel(
    val speed: Double? = null,
    val deg: Int? = null,
    val gust: Double? = null
)

data class RainModel(
    val threeHours: Double? = null
)

data class SysModel(
    val pod: String? = null
)

data class CityResponseModel(
    val id: Int? = null,
    val name: String? = null,
    val cord: CordModel? = null,
    val country: String? = null,
    val population: Int? = null,
    val timezone: Int? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null
)

data class CordModel(
    val lat: Double? = null,
    val lon: Double? = null
)
