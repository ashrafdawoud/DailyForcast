package com.baims.dailyforcast.presentation.screens.model.event

import com.baims.dailyforcast.domain.model.CityModel

sealed class ForeCastEvent {
    data class OnSelectCity(val cityModel: CityModel) : ForeCastEvent()
}