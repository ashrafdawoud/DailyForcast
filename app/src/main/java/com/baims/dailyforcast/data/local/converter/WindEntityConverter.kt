package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.WindEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WindEntityConverter {

    @TypeConverter
    fun fromWindEntity(wind: WindEntity?): String? {
        if (wind == null) return null
        val gson = Gson()
        val type = object : TypeToken<WindEntity>() {}.type
        return gson.toJson(wind, type)
    }

    @TypeConverter
    fun toWindEntity(windString: String?): WindEntity? {
        if (windString == null) return null
        val gson = Gson()
        val type = object : TypeToken<WindEntity>() {}.type
        return gson.fromJson(windString, type)
    }
}
