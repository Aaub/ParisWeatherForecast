package com.aaub.usecase.mapper

import com.aaub.model.Detail
import com.aaub.model.GivenPointWeather
import com.aaub.repository.weather.model.WeatherForecastApiResponse
import com.aaub.usecase.utils.getDayOfYear

object WeatherMapper {

    fun WeatherForecastApiResponse.toBusinessModel() {
        val refDate = getDayOfYear(list.first().dt)
        val index = 1
        val firstDay = mutableListOf<GivenPointWeather>()
        while (getDayOfYear(list[index].dt) == refDate) {
            firstDay.add(
                GivenPointWeather(
                    date =,
                    time =,
                    imageUrl =,
                    tempCurrent =,
                    tempMin =,
                    tempMax =,
                    detail = Detail(
                        atmoPressure =,
                        humidityRate =,
                        windSpeed =,
                        cloudinessRate =,
                        rainVolume =,
                        snowHeight =
                    )
                )
            )
        }


    }


    fun test() {
        val codonTable = mapOf(
            "ATT" to "Isoleucine",
            "CAA" to "Glutamine",
            "CGC" to "Arginine",
            "GGC" to "Glycine"
        )
        val dnaFragment = "ATTCGCGGCCGCCAA"

        val proteins = dnaFragment.chunked(3) {
            codonTable[it.toString()] ?: error("Unknown codon")
        }

        println(proteins) // [Isoleucine, Arginine, Glycine, Arginine, Glutamine]
    }
}

