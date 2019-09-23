package com.aaub.model

import java.io.Serializable
import java.util.*

data class GivenPointWeather(
    val date: Calendar,
    val time: Calendar,
    val imageUrl: String,
    val tempCurrent: Double,
    val tempMin: Double,
    val tempMax: Double,
    val detail: Detail
) : Serializable