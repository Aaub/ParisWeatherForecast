package com.aaub.parisweatherforecast.fivedays.dayforcastview

import java.io.Serializable

data class DayForecastViewData(
    val date: String,
    val pictoUrl: String,
    val currTemp: Double,
    val minTemp: Double,
    val maxTemp: Double
) : Serializable