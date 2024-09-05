package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.MainEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainEntityConverter {
    @TypeConverter
    fun fromWeatherList(weatherList: List<MainEntity>?): String? {
        if (weatherList == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<MainEntity>>() {}.type
        return gson.toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeatherList(weatherListString: String?): List<MainEntity>? {
        if (weatherListString == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<MainEntity>>() {}.type
        return gson.fromJson(weatherListString, type)
    }
}