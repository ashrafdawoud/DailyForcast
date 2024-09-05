package com.baims.dailyforcast.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baims.dailyforcast.data.local.entity.WeatherResponseEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherTable(weatherResponse: WeatherResponseEntity)

    @Query("SELECT * FROM WeatherResponseEntity WHERE cityId = :cityId")
    suspend fun getAllWeatherResponses(cityId: Int): List<WeatherResponseEntity>

    @Query("DELETE FROM WeatherResponseEntity")
    suspend fun deleteAllWeatherResponses()
}