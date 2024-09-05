package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.CityEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CityEntityConverter {

    @TypeConverter
    fun fromCityEntity(city: CityEntity?): String? {
        if (city == null) return null
        val gson = Gson()
        val type = object : TypeToken<CityEntity>() {}.type
        return gson.toJson(city, type)
    }

    @TypeConverter
    fun toCityEntity(cityString: String?): CityEntity? {
        if (cityString == null) return null
        val gson = Gson()
        val type = object : TypeToken<CityEntity>() {}.type
        return gson.fromJson(cityString, type)
    }
}