package com.aaub.repository.weather.model

import java.io.Serializable

data class Wind(
    val deg: Double,
    val speed: Double
) : Serializable