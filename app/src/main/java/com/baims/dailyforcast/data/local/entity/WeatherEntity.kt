package com.baims.dailyforcast.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.baims.dailyforcast.data.local.converter.*

@Entity
data class WeatherResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cityId: Int,
    val cod: String,
    val message: Int,
    val cnt: Int,
    @TypeConverters(WeatherItemListConverter::class)
    val list: List<WeatherItemEntity>,
    @TypeConverters(CityEntityConverter::class)
    val city: CityEntity
)

data class WeatherItemEntity(
    val id: Long = 0,
    val dt: Long,
    @TypeConverters(MainEntityConverter::class)
    val main: MainEntity,
    @TypeConverters(WeatherListConverter::class)
    val weather: List<WeatherEntity>,
    @TypeConverters(CloudsEntityConverter::class)
    val clouds: CloudsEntity,
    @TypeConverters(WindEntityConverter::class)
    val wind: WindEntity,
    val visibility: Int,
    val pop: Double,
    @TypeConverters(RainEntity::class)
    val rain: RainEntity?,
    @TypeConverters(SysEntityConverter::class)
    val sys: SysEntity,
    val dtTxt: String
)

data class MainEntity(
    val id: Long = 0,
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val seaLevel: Int,
    val grndLevel: Int,
    val humidity: Int,
    val tempKf: Double
)

data class WeatherEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CloudsEntity(
    val id: Long = 0,
    val all: Int
)

data class WindEntity(
    val id: Long = 0,
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class RainEntity(
    val id: Long = 0,
    val threeHours: Double
)

data class SysEntity(
    val pod: String
)

data class CityEntity(
    val id: Int,
    val name: String,
    @TypeConverters(CordEntityConverter::class)
    val cord: CordEntity,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class CordEntity(
    val id: Long = 0,
    val lat: Double,
    val lon: Double
)
