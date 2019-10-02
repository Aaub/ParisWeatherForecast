package com.aaub.repository.weather.model

import java.io.Serializable

data class Infos(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val rain: Rain?,
    val snow: Snow?,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
) : Serializable