package com.aaub.model

import java.io.Serializable

data class DayWeather(
    val givenPointWeathers: List<GivenPointWeather>
) : Serializable