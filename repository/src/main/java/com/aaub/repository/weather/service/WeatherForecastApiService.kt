package com.aaub.repository.weather.service

import com.aaub.repository.weather.model.WeatherForecastApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApiService {

    @GET("forecast")
    suspend fun getParisWeather(
        @Query("id") cityId: String,
        @Query("APPID") apiKey: String,
        @Query("units") units: String
    ): WeatherForecastApiResponse


}