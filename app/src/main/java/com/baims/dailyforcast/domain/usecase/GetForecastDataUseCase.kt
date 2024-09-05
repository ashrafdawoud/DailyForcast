package com.baims.dailyforcast.domain.usecase

import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.model.WeatherResponseModel
import com.baims.dailyforcast.domain.repository.ForecastRepository
import com.baims.dailyforcast.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForecastDataUseCase @Inject constructor(private val forecastRepository: ForecastRepository) {
    suspend operator fun invoke(cityModel: CityModel): Flow<DataState<WeatherResponseModel>> =
        forecastRepository.getForecastData(cityModel)
}
