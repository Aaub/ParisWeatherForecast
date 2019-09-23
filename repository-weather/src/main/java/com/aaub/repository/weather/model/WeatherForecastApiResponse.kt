package com.aaub.repository.weather.model

import java.io.Serializable

data class WeatherForecastApiResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Infos>,
    val message: Double
) : Serializable