package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.CordEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CordEntityConverter {

    @TypeConverter
    fun fromCordEntity(cord: CordEntity?): String? {
        if (cord == null) return null
        val gson = Gson()
        val type = object : TypeToken<CordEntity>() {}.type
        return gson.toJson(cord, type)
    }

    @TypeConverter
    fun toCordEntity(cordString: String?): CordEntity? {
        if (cordString == null) return null
        val gson = Gson()
        val type = object : TypeToken<CordEntity>() {}.type
        return gson.fromJson(cordString, type)
    }
}
