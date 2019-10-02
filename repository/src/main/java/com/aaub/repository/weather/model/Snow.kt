package com.aaub.repository.weather.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Snow(
    @SerializedName("3h") val height: Double
) : Serializable