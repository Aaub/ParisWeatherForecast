package com.aaub.repository.weather.service

import com.aaub.repository.weather.ConstantValue.WEATHER_FORECAST_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherForecastApiServiceFactory {

    fun createWeatherForecastService(): WeatherForecastApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(WEATHER_FORECAST_URL)
        .build()
        .create(WeatherForecastApiService::class.java)
}

