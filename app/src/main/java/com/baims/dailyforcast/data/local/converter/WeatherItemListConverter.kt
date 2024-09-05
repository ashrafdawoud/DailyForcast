package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.WeatherItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherItemListConverter {
    @TypeConverter
    fun fromWeatherItemList(weatherItemList: List<WeatherItemEntity>?): String? {
        if (weatherItemList == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<WeatherItemEntity>>() {}.type
        return gson.toJson(weatherItemList, type)
    }

    @TypeConverter
    fun toWeatherItemList(weatherItemListString: String?): List<WeatherItemEntity>? {
        if (weatherItemListString == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<WeatherItemEntity>>() {}.type
        return gson.fromJson(weatherItemListString, type)
    }
}
