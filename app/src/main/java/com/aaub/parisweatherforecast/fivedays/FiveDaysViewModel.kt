package com.aaub.parisweatherforecast.fivedays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaub.model.FiveDaysWeatherForecast
import com.aaub.usecase.FiveDaysParisWeather
import kotlinx.coroutines.launch

class FiveDaysViewModel : ViewModel() {
    private val _fiveDaysWeather = MutableLiveData<FiveDaysWeatherForecast>()
    val fiveDaysWeather: LiveData<FiveDaysWeatherForecast> = _fiveDaysWeather

    private val fiveDaysWeatherForecast by lazy {
        FiveDaysParisWeather()
    }

    init {
        fiveDaysWeatherForecast()
    }

    private fun fiveDaysWeatherForecast() {
        viewModelScope.launch {
            _fiveDaysWeather.postValue(fiveDaysWeatherForecast.getParisFiveDaysWeatherForecast())
        }
    }
}
