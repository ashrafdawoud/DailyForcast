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
                        forecastDataLocalDataSource.insertWeatherTable(
                            weatherResponseModel.toWeatherResponseEntity(cityModel.id)
                        )
                        emit(DataState.Success(data = weatherResponseModel))
                    } ?: run {
                        emit(handleEmptyResponse(cityModel.id))
                    }
                } else {
                    emit(handleEmptyResponse(cityModel.id))
                }
            } catch (e: Exception) {
                emit(handleEmptyResponse(cityModel.id))
            }
        }

    private suspend fun handleEmptyResponse(cityId: Int): DataState<WeatherResponseModel> {
        val cachedWeather = forecastDataLocalDataSource.getWeatherByCity(cityId)
        return if (cachedWeather.isNotEmpty()) {
            DataState.Success(
                isFromCache = true,
                data = cachedWeather.first().toWeatherResponseModel()
            )
        } else {
            DataState.Error(Exception("no data found"))
        }
    }
}
