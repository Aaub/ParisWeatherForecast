package com.aaub.usecase

import com.aaub.model.DayWeather
import com.aaub.model.Detail
import com.aaub.model.FiveDaysWeatherForecast
import com.aaub.model.GivenPointWeather
import com.aaub.repository.weather.ConstantValue
import com.aaub.repository.weather.service.WeatherForecastApiServiceFactory
import com.aaub.usecase.mapper.toBusinessModel
import com.aaub.usecase.utils.WeatherApiUtils
import com.aaub.usecase.utils.reduce

class FiveDaysParisWeather {

    private val DAY = "d"
    private val NIGHT = "n"
    private val weatherForecastService =
        WeatherForecastApiServiceFactory.createWeatherForecastService()


    suspend fun getParisFiveDaysWeatherForecast() =
        FiveDaysWeatherForecast(
            weatherForecastService
                .getParisWeather(
                    ConstantValue.CITY_CODE_PARIS,
                    ConstantValue.APP_ID,
                    ConstantValue.METRIC_UNITS
                ).toBusinessModel().dayWeathers.map {
                DayWeather(listOf(dayRecap(it.givenPointWeathers)))
            }.subList(0, 5)
        )


    private fun dayRecap(givenPointWeathers: List<GivenPointWeather>): GivenPointWeather {
        var averageMinTemp = 100.0
        var averageMaxTemp = 0.0
        var averageDayTemp = 0.0
        val averageWeather = mutableListOf<String>()
        val averageAtmoPressure = mutableListOf<Double>()
        val averageHumidityRate = mutableListOf<Int>()
        val averageWindSpeed = mutableListOf<Double>()
        val averageCloudiness = mutableListOf<Int>()
        val averageRainVolume = mutableListOf<Double>()
        val averageSnowHeiht = mutableListOf<Double>()
        val listSize = givenPointWeathers.size


        givenPointWeathers.forEach {
            averageMinTemp = if (it.tempMin < averageMinTemp) it.tempMin else averageMinTemp
            averageMaxTemp = if (it.tempMax > averageMaxTemp) it.tempMax else averageMaxTemp
            averageDayTemp += it.tempCurrent
            averageWeather.add(it.image)
            averageAtmoPressure.add(it.detail.atmoPressure)
            averageHumidityRate.add(it.detail.humidityRate)
            averageWindSpeed.add(it.detail.windSpeed)
            averageCloudiness.add(it.detail.cloudinessRate)
            averageRainVolume.add(it.detail.rainVolume)
            averageSnowHeiht.add(it.detail.snowHeight)
        }

        return GivenPointWeather(
            date = givenPointWeathers.first().date,
            image = getAvarageWeatherForPicto(
                averageWeather
            )?.let { WeatherApiUtils.bigIconUrl(it) }
                ?: "https://weatherforce.org/wp-content/uploads/2017/09/picto-weather-force.png",
            tempCurrent = (averageDayTemp / listSize).reduce(),
            tempMin = averageMinTemp.reduce(),
            tempMax = averageMaxTemp.reduce(),
            detail = Detail(
                atmoPressure = averageAtmoPressure.sum() / listSize,
                humidityRate = averageHumidityRate.sum() / listSize,
                windSpeed = averageWindSpeed.sum() / listSize,
                cloudinessRate = averageCloudiness.sum() / listSize,
                rainVolume = averageRainVolume.sum() / listSize,
                snowHeight = averageSnowHeiht.sum() / listSize
            )

        )
    }


    private fun getAvarageWeatherForPicto(pictoIds: List<String>): String? {
        val dayOrNights = mutableListOf<String>()
        val avaragePictoNumber = pictoIds.map {
            dayOrNights.add(it.subSequence(it.length - 1, it.length).toString())
            val pictoNumber = it.subSequence(0, it.length - 1).toString().toInt()
            if (pictoNumber == 13 || pictoNumber == 50) {
                return "$pictoNumber${dayOrNights.first()}"
            } else {
                pictoNumber
            }
        }
        val realPictoNumber = getRealPictoId(avaragePictoNumber.sum() / pictoIds.size)
        val realDayOrNight = getDayOrNight(dayOrNights)
        return when {
            realPictoNumber == null -> null
            realPictoNumber < 10 -> "0$realPictoNumber$realDayOrNight"
            realPictoNumber >= 10 -> "$realPictoNumber$realDayOrNight"
            else -> null
        }

    }

    private fun getDayOrNight(values: List<String>): String {
        val value = values.map {
            when (it) {
                NIGHT -> 0
                else -> 1
            }
        }.sum()
        return if (value >= 4) {
            DAY
        } else {
            NIGHT
        }
    }

    private fun getRealPictoId(pictoIdNummber: Int): Int? = when (pictoIdNummber) {
        in 1..4, in 9..11 -> pictoIdNummber
        5, 6 -> 4
        7, 8 -> 9
        0 -> 1
        else -> null
    }
}