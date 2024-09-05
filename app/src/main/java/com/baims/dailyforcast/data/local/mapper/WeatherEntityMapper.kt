package com.baims.dailyforcast.data.local.mapper

import com.baims.dailyforcast.data.local.entity.CityEntity
import com.baims.dailyforcast.data.local.entity.CloudsEntity
import com.baims.dailyforcast.data.local.entity.CordEntity
import com.baims.dailyforcast.data.local.entity.MainEntity
import com.baims.dailyforcast.data.local.entity.RainEntity
import com.baims.dailyforcast.data.local.entity.SysEntity
import com.baims.dailyforcast.data.local.entity.WeatherEntity
import com.baims.dailyforcast.data.local.entity.WeatherItemEntity
import com.baims.dailyforcast.data.local.entity.WeatherResponseEntity
import com.baims.dailyforcast.data.local.entity.WindEntity
import com.baims.dailyforcast.domain.model.CityModel
import com.baims.dailyforcast.domain.model.CloudsModel
import com.baims.dailyforcast.domain.model.CordModel
import com.baims.dailyforcast.domain.model.MainModel
import com.baims.dailyforcast.domain.model.RainModel
import com.baims.dailyforcast.domain.model.SysModel
import com.baims.dailyforcast.domain.model.WeatherItemModel
import com.baims.dailyforcast.domain.model.WeatherModel
import com.baims.dailyforcast.domain.model.WeatherResponseModel
import com.baims.dailyforcast.domain.model.WindModel


fun WeatherResponseModel.toWeatherResponseEntity(): WeatherResponseEntity =
    WeatherResponseEntity(
        cod = cod,
        cityId = city.id,
        message = message,
        cnt = cnt,
        list = list.map { it.toWeatherItemEntity() },
        city = city.toCityModel()
    )
fun WeatherResponseEntity.toWeatherResponseModel(): WeatherResponseModel =
    WeatherResponseModel(
        cod = cod,
        message = message,
        cnt = cnt,
        list = list.map { it.toWeatherItemModel() },
        city = city.toCityModel()
    )
fun WeatherItemEntity.toWeatherItemModel(): WeatherItemModel =
    WeatherItemModel(
        dt = dt,
        main = main.toWeatherModel(),
        weather = weather.map { it.toWeatherModel() },
        clouds = clouds.toCloudsModel(),
        wind = wind.toWindModel(),
        visibility = visibility,
        pop = pop,
        rain = rain?.toRainModel(),
        sys = sys.toSysModel(),
        dtTxt = dtTxt
    )

fun WeatherItemModel.toWeatherItemEntity(): WeatherItemEntity =
    WeatherItemEntity(
        dt = dt,
        main = main.toWeatherModel(),
        weather = weather.map { it.toWeatherEntity() },
        clouds = clouds.toCloudsEntity(),
        wind = wind.toWindEntity(),
        visibility = visibility,
        pop = pop,
        rain = rain?.toRainEntity(),
        sys = sys.toSysEntity(),
        dtTxt = dtTxt
    )

fun SysEntity.toSysModel(): SysModel = SysModel(pod = pod)
fun SysModel.toSysEntity(): SysEntity = SysEntity(pod = pod)
fun RainEntity.toRainModel(): RainModel = RainModel(threeHours = threeHours)
fun RainModel.toRainEntity(): RainEntity = RainEntity(threeHours = threeHours)
fun CloudsEntity.toCloudsModel(): CloudsModel = CloudsModel(all = all)
fun CloudsModel.toCloudsEntity(): CloudsEntity = CloudsEntity(all = all)
fun WindEntity.toWindModel(): WindModel = WindModel(speed = speed, deg = deg, gust = gust)
fun WindModel.toWindEntity(): WindEntity = WindEntity(speed = speed, deg = deg, gust = gust)
fun WeatherEntity.toWeatherModel(): WeatherModel =
    WeatherModel(id = id, main = main, description = description, icon = icon)
fun WeatherModel.toWeatherEntity(): WeatherEntity =
    WeatherEntity(id = id, main = main, description = description, icon = icon)

fun MainEntity.toWeatherModel(): MainModel =
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

fun MainModel.toWeatherModel(): MainEntity =
    MainEntity(
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

fun CityEntity.toCityModel(): CityModel =
    CityModel(
        id = id,
        name = name,
        cord = cord.toModel(),
        country = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

fun CityModel.toCityModel(): CityEntity =
    CityEntity(
        id = id,
        name = name,
        cord = cord.toModel(),
        country = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )

fun CordEntity.toModel(): CordModel =
    CordModel(lat = lat, lon = lon)
fun CordModel.toModel(): CordEntity =
    CordEntity(lat = lat, lon = lon)