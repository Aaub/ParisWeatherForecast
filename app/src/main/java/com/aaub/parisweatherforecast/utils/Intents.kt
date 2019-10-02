package com.aaub.parisweatherforecast.utils

import android.content.Context
import android.content.Intent
import com.aaub.model.Detail
import com.aaub.parisweatherforecast.forecastdetail.ForecastDetailActivity

object Intents {

    const val INTENT_EXTRA_FORECAST_DETAIL = "INTENT_EXTRA_FORECAST_DETAIL"

    fun forecastDetail(context: Context, detail: Detail) =
        Intent(context, ForecastDetailActivity::class.java).apply {
            putExtra(INTENT_EXTRA_FORECAST_DETAIL, detail)
        }
}