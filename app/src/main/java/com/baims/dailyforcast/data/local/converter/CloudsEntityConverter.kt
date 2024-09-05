package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.CloudsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CloudsEntityConverter {

    @TypeConverter
    fun fromCloudsEntity(clouds: CloudsEntity?): String? {
        if (clouds == null) return null
        val gson = Gson()
        val type = object : TypeToken<CloudsEntity>() {}.type
        return gson.toJson(clouds, type)
    }

    @TypeConverter
    fun toCloudsEntity(cloudsString: String?): CloudsEntity? {
        if (cloudsString == null) return null
        val gson = Gson()
        val type = object : TypeToken<CloudsEntity>() {}.type
        return gson.fromJson(cloudsString, type)
    }
}
