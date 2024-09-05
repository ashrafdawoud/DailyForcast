package com.baims.dailyforcast.domain.repository

import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.model.WeatherResponseModel
import com.baims.dailyforcast.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun getForecastData(cityModel: CityModel):Flow<DataState<WeatherResponseModel>>
}