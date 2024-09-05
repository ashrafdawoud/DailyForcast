package com.baims.dailyforcast.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.usecase.GetForecastDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase
) : ViewModel() {
    init {
        getForecastData(
            CityModel(
                id = 1,
                cityNameAr = "asa",
                cityNameEn = "asas",
                lat = 25.2048,
                lon = 55.2708
            )
        )
    }

    private fun getForecastData(cityModel: CityModel) {
        viewModelScope.launch {
            getForecastDataUseCase.invoke(cityModel).collect {
                Log.e("error from api  -> ", it.toString())
            }
        }
    }
}