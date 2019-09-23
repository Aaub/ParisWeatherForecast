package com.aaub.model

import java.io.Serializable

data class Detail(
    val atmoPressure: Double,
    val humidityRate: Int,
    val windSpeed: Double,
    val cloudinessRate: Int,
    val rainVolume: Double,
    val snowHeight: Double
) : Serializable