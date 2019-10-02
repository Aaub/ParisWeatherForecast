package com.aaub.usecase.utils

object WeatherApiUtils {
    private const val ICON_EXTENSION = ".png"
    private const val BIG_ICON_EXTENSION = "@2x$ICON_EXTENSION"
    private const val PICTO_WEATHER_URL = "http://openweathermap.org/img/wn/"

    fun bigIconUrl(iconId: String) = "$PICTO_WEATHER_URL$iconId$BIG_ICON_EXTENSION"
}
