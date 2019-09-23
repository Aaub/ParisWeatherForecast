package com.aaub.repository.weather.service

import com.aaub.repository.weather.ConstantValue.OPEN_WEATHER_MAP_URL
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherForecastApiServiceFactory {


    fun createService(): WeatherForecastApiService = Retrofit.Builder()
        .baseUrl(OPEN_WEATHER_MAP_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(WeatherForecastApiService::class.java)
}