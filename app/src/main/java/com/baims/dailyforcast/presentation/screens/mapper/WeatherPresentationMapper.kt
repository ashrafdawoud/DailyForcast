package com.baims.dailyforcast.presentation.screens.mapper

import com.baims.dailyforcast.domain.model.WeatherItemModel
import com.baims.dailyforcast.domain.model.WeatherModel
import com.baims.dailyforcast.domain.model.WeatherResponseModel
import com.baims.dailyforcast.domain.utils.TemperatureStatus
import com.baims.dailyforcast.presentation.screens.model.state.WeatherPresentationModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun WeatherResponseModel.toWeatherPresentationListModel(): List<WeatherPresentationModel>? =
    list?.map {
        it.toWeatherPresentationModel()
    }

fun WeatherItemModel.toWeatherPresentationModel(): WeatherPresentationModel =
    WeatherPresentationModel(
        dayDate = dtTxt ?: "",
        dayName = getDayOfWeekName(dtTxt?:""),
        temperatureDegrees = "${(main?.temp?.minus(273.15))?.toInt()}°",
        wetBows = "${main?.humidity} %",
        windSpeed = "${wind?.speed} KM/H",
        cloud = "${clouds?.all} %",
        iconUrl = "https://openweathermap.org/img/wn/${
            weather?.first()?.icon?.replace(
                "n",
                "d"
            )
        }@4x.png",
        temperatureStatus = weather?.first()?.toTemperatureStatus() ?: TemperatureStatus.NON
    )

fun WeatherModel.toTemperatureStatus(): TemperatureStatus =
    when {
        (description ?: "") == TemperatureStatus.MIST.status -> TemperatureStatus.MIST
        (description ?: "") == TemperatureStatus.RAIN.status -> TemperatureStatus.RAIN
        (description ?: "") == TemperatureStatus.SNOWY.status -> TemperatureStatus.SNOWY
        (description ?: "") == TemperatureStatus.CLEAR_SKY.status -> TemperatureStatus.CLEAR_SKY
        (description ?: "") == TemperatureStatus.FEW_CLOUDS.status -> TemperatureStatus.FEW_CLOUDS
        (description ?: "") == TemperatureStatus.BROKEN_CLOUDS.status -> TemperatureStatus.BROKEN_CLOUDS
        (description ?: "") == TemperatureStatus.SCATTERED_CLOUDS.status -> TemperatureStatus.SCATTERED_CLOUDS
        (description ?: "") == TemperatureStatus.SHOWER_RAIN.status -> TemperatureStatus.SHOWER_RAIN
        (description ?: "") == TemperatureStatus.THUNDER_STORM.status -> TemperatureStatus.THUNDER_STORM
        else -> {TemperatureStatus.NON}
    }
fun getDayOfWeekName(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val localDate = LocalDate.parse(dateString, formatter)
    val dayOfWeek = localDate.dayOfWeek
    val dayOfWeekName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    return dayOfWeekName
}