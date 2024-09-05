package com.baims.dailyforcast.data.local.converter

import androidx.room.TypeConverter
import com.baims.dailyforcast.data.local.entity.SysEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SysEntityConverter {

    @TypeConverter
    fun fromSysEntity(sys: SysEntity?): String? {
        if (sys == null) return null
        val gson = Gson()
        val type = object : TypeToken<SysEntity>() {}.type
        return gson.toJson(sys, type)
    }

    @TypeConverter
    fun toSysEntity(sysString: String?): SysEntity? {
        if (sysString == null) return null
        val gson = Gson()
        val type = object : TypeToken<SysEntity>() {}.type
        return gson.fromJson(sysString, type)
    }
}
