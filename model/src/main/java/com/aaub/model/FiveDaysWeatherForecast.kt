package com.aaub.model

import java.io.Serializable

data class FiveDaysWeatherForecast(
    val dayWeathers: List<DayWeather>
) : Serializable