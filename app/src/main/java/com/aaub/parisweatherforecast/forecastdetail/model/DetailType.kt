package com.aaub.parisweatherforecast.forecastdetail.model

import androidx.annotation.StringRes
import com.aaub.parisweatherforecast.R

enum class DetailType(
    @StringRes val unitResId: Int,
    @StringRes val titleResId: Int
) {
    PRESSURE(R.string.pressure, R.string.title_atmospheric_pressure),
    HUMIDITY(R.string.rate, R.string.title_humidity_rate),
    WIND(R.string.speed, R.string.title_wind_speed),
    CLOUD(R.string.rate, R.string.title_cloudiness_rate),
    RAIN(R.string.height, R.string.title_rain_volume),
    SNOW(R.string.height, R.string.title_snow_height)
}