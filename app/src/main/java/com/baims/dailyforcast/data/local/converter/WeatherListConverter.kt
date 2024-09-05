package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.WeatherEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherListConverter {

    @TypeConverter
    fun fromWeatherList(weatherList: List<WeatherEntity>?): String? {
        if (weatherList == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return gson.toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeatherList(weatherListString: String?): List<WeatherEntity>? {
        if (weatherListString == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return gson.fromJson(weatherListString, type)
    }
}
