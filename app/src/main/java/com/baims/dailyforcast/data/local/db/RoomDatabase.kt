package com.baims.dailyforcast.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.baims.dailyforcast.data.local.converter.CityEntityConverter
import com.baims.dailyforcast.data.local.converter.CloudsEntityConverter
import com.baims.dailyforcast.data.local.converter.CordEntityConverter
import com.baims.dailyforcast.data.local.converter.MainEntityConverter
import com.baims.dailyforcast.data.local.converter.RainEntityConverter
import com.baims.dailyforcast.data.local.converter.SysEntityConverter
import com.baims.dailyforcast.data.local.converter.WeatherItemListConverter
import com.baims.dailyforcast.data.local.converter.WeatherListConverter
import com.baims.dailyforcast.data.local.converter.WindEntityConverter
import com.baims.dailyforcast.data.local.dao.WeatherDao
import com.baims.dailyforcast.data.local.entity.WeatherEntity

@Database(
    entities = [WeatherEntity::class], version = 1, exportSchema = false
)
@TypeConverters(
    CityEntityConverter::class,
    CloudsEntityConverter::class,
    CordEntityConverter::class,
    MainEntityConverter::class,
    RainEntityConverter::class,
    SysEntityConverter::class,
    WeatherItemListConverter::class,
    WeatherListConverter::class,
    WindEntityConverter::class,
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    companion object {
        const val DATABASE_NAME: String = "general_database"
    }
}