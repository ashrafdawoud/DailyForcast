package com.baims.dailyforcast.data.repository

import com.baims.dailyforcast.data.local.datasource.ForecastDataLocalDataSource
import com.baims.dailyforcast.data.local.mapper.toWeatherResponseEntity
import com.baims.dailyforcast.data.local.mapper.toWeatherResponseModel
import com.baims.dailyforcast.data.remote.datasource.ForecastDataRemoteDataSource
import com.baims.dailyforcast.data.remote.mapper.toWeatherResponseModel
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.model.WeatherResponseModel
import com.baims.dailyforcast.domain.repository.ForecastRepository
import com.baims.dailyforcast.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForecastRepositoryImp(
    private val forecastDataRemoteDataSource: ForecastDataRemoteDataSource,
    private val forecastDataLocalDataSource: ForecastDataLocalDataSource
) : ForecastRepository {

    override suspend fun getForecastData(cityModel: CityModel): Flow<DataState<WeatherResponseModel>> =
        flow {
            emit(DataState.Loading)
            try {
                val weatherResponse = forecastDataRemoteDataSource.getForecastData(
                    lat = cityModel.lat,
                    lon = cityModel.lon
                )

                if (weatherResponse.isSuccessful) {
                    weatherResponse.body()?.let { responseBody ->
                        val weatherResponseModel = responseBody.toWeatherResponseModel()
                        // Insert into local data source
                        forecastDataLocalDataSource.insertWeatherTable(
                            weatherResponseModel.toWeatherResponseEntity(cityModel.id)
                        )
                        emit(DataState.Success(weatherResponseModel))
                    } ?: run {
                        // Handle null response body case
                        handleEmptyResponse(cityModel.id)
                    }
                } else {
                    // Handle unsuccessful response, possibly with response code
                    handleEmptyResponse(cityModel.id)
                }
            } catch (e: Exception) {
                // Log the exception for debugging
                emit(DataState.Error(e))
            }
        }

    private suspend fun handleEmptyResponse(cityId: Int): Flow<DataState<WeatherResponseModel>> =
        flow {
            val cachedWeather = forecastDataLocalDataSource.getWeatherByCity(cityId)
            if (cachedWeather.isNotEmpty()) {
                emit(DataState.Success(cachedWeather.first().toWeatherResponseModel()))
            } else {
                emit(DataState.Error(Exception("no data found")))
            }
        }
}