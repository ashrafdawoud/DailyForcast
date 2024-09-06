package com.baims.dailyforcast.presentation.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baims.dailyforcast.R
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.usecase.GetForecastDataUseCase
import com.baims.dailyforcast.domain.utils.DataState
import com.baims.dailyforcast.presentation.screens.mapper.toWeatherPresentationListModel
import com.baims.dailyforcast.presentation.screens.model.event.ForeCastEvent
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getForecastDataUseCase: GetForecastDataUseCase
) : ViewModel() {

    private val _weatherPresentationModel: MutableStateFlow<List<WeatherPresentationModel>> =
        MutableStateFlow(listOf())
    val weatherPresentationModel: StateFlow<List<WeatherPresentationModel>> =
        _weatherPresentationModel

    private val _cites: MutableStateFlow<Array<CityModel>> = MutableStateFlow(arrayOf())
    val cites: StateFlow<Array<CityModel>> = _cites

    private val _isFromCache: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFromCache: StateFlow<Boolean> = _isFromCache

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getCites()
    }

    fun onTriggerEvent(event: ForeCastEvent) {
        when (event) {
            is ForeCastEvent.OnSelectCity -> {
                getForecastData(event.cityModel)
            }
        }
    }

    private fun getForecastData(cityModel: CityModel) {
        viewModelScope.launch {
            getForecastDataUseCase.invoke(cityModel).collect {
                when (it) {
                    is DataState.Loading -> {
                        _isLoading.value = true
                    }

                    is DataState.Success -> {
                        _weatherPresentationModel.value = it.data.toWeatherPresentationListModel() ?: listOf()
                        _isFromCache.value = it.isFromCache
                        _isLoading.value = false
                    }

                    is DataState.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    private fun getCites() {
        val inputStream = context.resources.openRawResource(R.raw.cites)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        val json = String(buffer)
        val cites = Gson().fromJson(json, Array<CityModel>::class.java)
        _cites.value = cites
        getForecastData(cites.first())
    }
}