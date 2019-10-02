package com.aaub.usecase.mapper

import com.aaub.model.DayWeather
import com.aaub.model.Detail
import com.aaub.model.FiveDaysWeatherForecast
import com.aaub.model.GivenPointWeather
import com.aaub.repository.weather.model.Infos
import com.aaub.repository.weather.model.WeatherForecastApiResponse
import com.aaub.usecase.utils.buildCalendar
import java.util.*


fun WeatherForecastApiResponse.toBusinessModel() = FiveDaysWeatherForecast(
    list
        .groupBy {
            buildCalendar(it.dt).get(Calendar.DAY_OF_YEAR)
        }.map {
            DayWeather(it.value.map { infos ->
                infos.toBusinessModel()
            })
        }
)

fun Infos.toBusinessModel() = GivenPointWeather(
    date = buildCalendar(dt),
    image = this.weather.first().icon,
    tempCurrent = main.temp,
    tempMin = main.temp_min,
    tempMax = main.temp_max,
    detail = Detail(
        atmoPressure = main.pressure,
        humidityRate = main.humidity,
        windSpeed = wind.speed,
        cloudinessRate = clouds.all,
        rainVolume = rain?.volume ?: 0.0,
        snowHeight = snow?.height ?: 0.0
    )
)

