package com.aaub.repository.weather.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApiService {

    @GET("forcast")
    fun getParisWeather(@Query("APPID") apiKey: String, @Query("id") cityId: String): Deferred<WeatherForecastApiService>
}