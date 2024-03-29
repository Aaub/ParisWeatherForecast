package com.aaub.repository.weather.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rain(
    @SerializedName("3h") val volume: Double?
) : Serializable