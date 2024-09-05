package com.baims.dailyforcast.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("cod")
    val cod: String? = null,
    @SerializedName("message")
    val message: Int? = null,
    @SerializedName("cnt")
    val cnt: Int? = null,
    @SerializedName("list")
    val list: List<WeatherItemDto>? = null,
    @SerializedName("city")
    val city: CityDto? = null
)

data class WeatherItemDto(
    @SerializedName("dt")
    val dt: Long? = null,
    @SerializedName("main")
    val main: MainDto? = null,
    @SerializedName("weather")
    val weather: List<WeatherDto>? = null,
    @SerializedName("clouds")
    val clouds: CloudsDto? = null,
    @SerializedName("wind")
    val wind: WindDto? = null,
    @SerializedName("visibility")
    val visibility: Int? = null,
    @SerializedName("pop")
    val pop: Double? = null,
    @SerializedName("rain")
    val rain: RainDto? = null,
    @SerializedName("sys")
    val sys: SysDto? = null,
    @SerializedName("dt_txt")
    val dtTxt: String? = null
)

data class MainDto(
    @SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("feels_like")
    val feelsLike: Double? = null,
    @SerializedName("temp_min")
    val tempMin: Double? = null,
    @SerializedName("temp_max")
    val tempMax: Double? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("sea_level")
    val seaLevel: Int? = null,
    @SerializedName("grnd_level")
    val grndLevel: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("temp_kf")
    val tempKf: Double? = null
)

data class WeatherDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null
)

data class CloudsDto(
    @SerializedName("all")
    val all: Int? = null
)

data class WindDto(
    @SerializedName("speed")
    val speed: Double? = null,
    @SerializedName("deg")
    val deg: Int? = null,
    @SerializedName("gust")
    val gust: Double? = null
)

data class RainDto(
    @SerializedName("3h")
    val threeHours: Double? = null
)

data class SysDto(
    @SerializedName("pod")
    val pod: String? = null
)

data class CityDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("coord")
    val cord: CordDto? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("population")
    val population: Int? = null,
    @SerializedName("timezone")
    val timezone: Int? = null,
    @SerializedName("sunrise")
    val sunrise: Long? = null,
    @SerializedName("sunset")
    val sunset: Long? = null
)

data class CordDto(
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lon")
    val lon: Double? = null
)
