package com.baims.dailyforcast.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.baims.dailyforcast.data.local.converter.*

@Entity
data class WeatherResponseEntity(
    @PrimaryKey
    val cityId: Int? = null,
    val id: Long? = null,
    val cod: String? = null,
    val message: Int? = null,
    val cnt: Int? = null,
    @TypeConverters(WeatherItemListConverter::class)
    val list: List<WeatherItemEntity>? = null,
    @TypeConverters(CityEntityConverter::class)
    val city: CityEntity? = null
)

data class WeatherItemEntity(
    val id: Long? = null,
    val dt: Long? = null,
    @TypeConverters(MainEntityConverter::class)
    val main: MainEntity? = null,
    @TypeConverters(WeatherListConverter::class)
    val weather: List<WeatherEntity>? = null,
    @TypeConverters(CloudsEntityConverter::class)
    val clouds: CloudsEntity? = null,
    @TypeConverters(WindEntityConverter::class)
    val wind: WindEntity? = null,
    val visibility: Int? = null,
    val pop: Double? = null,
    @TypeConverters(RainEntityConverter::class)
    val rain: RainEntity? = null,
    @TypeConverters(SysEntityConverter::class)
    val sys: SysEntity? = null,
    val dtTxt: String? = null
)

data class MainEntity(
    val id: Long? = null,
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

data class WeatherEntity(
    val id: Int? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)

data class CloudsEntity(
    val id: Long? = null,
    val all: Int? = null
)

data class WindEntity(
    val id: Long? = null,
    val speed: Double? = null,
    val deg: Int? = null,
    val gust: Double? = null
)

data class RainEntity(
    val id: Long? = null,
    val threeHours: Double? = null
)

data class SysEntity(
    val pod: String? = null
)

data class CityEntity(
    val id: Int? = null,
    val name: String? = null,
    @TypeConverters(CordEntityConverter::class)
    val cord: CordEntity? = null,
    val country: String? = null,
    val population: Int? = null,
    val timezone: Int? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null
)

data class CordEntity(
    val id: Long? = null,
    val lat: Double? = null,
    val lon: Double? = null
)
