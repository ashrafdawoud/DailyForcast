package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.RainEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RainEntityConverter {

    @TypeConverter
    fun fromRainEntity(rain: RainEntity?): String? {
        if (rain == null) return null
        val gson = Gson()
        val type = object : TypeToken<RainEntity>() {}.type
        return gson.toJson(rain, type)
    }

    @TypeConverter
    fun toRainEntity(rainString: String?): RainEntity? {
        if (rainString == null) return null
        val gson = Gson()
        val type = object : TypeToken<RainEntity>() {}.type
        return gson.fromJson(rainString, type)
    }
}
