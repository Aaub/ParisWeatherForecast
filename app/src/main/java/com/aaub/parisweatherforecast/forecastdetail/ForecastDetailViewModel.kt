package com.aaub.parisweatherforecast.forecastdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aaub.model.Detail
import com.aaub.parisweatherforecast.forecastdetail.model.DetailType
import com.aaub.parisweatherforecast.forecastdetail.model.DetailType.*
import com.aaub.usecase.utils.reduce

class ForecastDetailViewModel : ViewModel() {
    private val _decomposedDetails = MutableLiveData<Map<DetailType, Double>>()
    val decomposedDetails: LiveData<Map<DetailType, Double>> = _decomposedDetails


    fun buildDecomposedDetails(detail: Detail?) {
        _decomposedDetails.postValue(detail?.let {
            mapOf(
                PRESSURE to it.atmoPressure.reduce(),
                HUMIDITY to it.humidityRate.toDouble().reduce(),
                WIND to it.windSpeed.reduce(),
                CLOUD to it.cloudinessRate.toDouble().reduce(),
                RAIN to it.rainVolume.reduce(),
                SNOW to it.snowHeight.reduce()
            )
        }?.filterNot {
            it.value == 0.0
        } ?: emptyMap())
    }
}
