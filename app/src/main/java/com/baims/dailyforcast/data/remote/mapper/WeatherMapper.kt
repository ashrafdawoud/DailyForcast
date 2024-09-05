package com.baims.dailyforcast.data.remote.mapper

import com.baims.dailyforcast.data.remote.dto.CityDto
import com.baims.dailyforcast.data.remote.dto.CloudsDto
import com.baims.dailyforcast.data.remote.dto.CordDto
import com.baims.dailyforcast.data.remote.dto.MainDto
import com.baims.dailyforcast.data.remote.dto.RainDto
import com.baims.dailyforcast.data.remote.dto.SysDto
import com.baims.dailyforcast.data.remote.dto.WeatherDto
import com.baims.dailyforcast.data.remote.dto.WeatherItemDto
import com.baims.dailyforcast.data.remote.dto.WeatherResponseDto
import com.baims.dailyforcast.data.remote.dto.WindDto
import com.baims.dailyforcast.domain.model.CityResponseModel
import com.baims.dailyforcast.domain.model.CloudsModel
import com.baims.dailyforcast.domain.model.CordModel
import com.baims.dailyforcast.domain.model.MainModel
import com.baims.dailyforcast.domain.model.RainModel
import com.baims.dailyforcast.domain.model.SysModel
import com.baims.dailyforcast.domain.model.WeatherItemModel
import com.baims.dailyforcast.domain.model.WeatherModel
import com.baims.dailyforcast.domain.model.WeatherResponseModel
import com.baims.dailyforcast.domain.model.WindModel

fun WeatherResponseDto.toWeatherResponseModel(): WeatherResponseModel =
    WeatherResponseModel(
        cod = cod,
        message = message,
        cnt = cnt,
        list = list?.map { it.toWeatherItemModel() },
        city = city?.toCityModel(),

        )

fun WeatherItemDto.toWeatherItemModel(): WeatherItemModel =
    WeatherItemModel(
        dt = dt,
        main = main?.toWeatherModel(),
        weather = weather?.map { it.toWeatherModel() },
        clouds = clouds?.toCloudsModel(),
        wind = wind?.toWindModel(),
        visibility = visibility,
        pop = pop,
        rain = rain?.toRainModel(),
        sys = sys?.toSysModel(),
        dtTxt = dtTxt
    )

fun SysDto.toSysModel(): SysModel = SysModel(pod = pod)
fun RainDto.toRainModel(): RainModel = RainModel(threeHours = threeHours)
fun CloudsDto.toCloudsModel(): CloudsModel = CloudsModel(all = all)
fun WindDto.toWindModel(): WindModel = WindModel(speed = speed, deg = deg, gust = gust)
fun WeatherDto.toWeatherModel(): WeatherModel =
    WeatherModel(id = id, main = main, description = description, icon = icon)

fun MainDto.toWeatherModel(): MainModel =
    MainModel(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        seaLevel = seaLevel,
        grndLevel = grndLevel,
        humidity = humidity,
        tempKf = tempKf
    )

fun CityDto.toCityModel(): CityResponseModel =
    CityResponseModel(
        id = id,
        name = name,
        cord = cord?.toModel(),
        country = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

fun CordDto.toModel(): CordModel =
    CordModel(lat = lat, lon = lon)