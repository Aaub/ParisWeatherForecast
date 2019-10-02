package com.aaub.parisweatherforecast.fivedays.dayforcastview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.aaub.parisweatherforecast.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_day_forecast.view.*

class DayForecastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_day_forecast, this)
    }

    var listener: Listener? = null

    interface Listener {
        fun onClickDetail()
    }

    fun setupView(dayForecastViewData: DayForecastViewData) {
        with(dayForecastViewData) {
            setupDate(date)
            setupPicto(pictoUrl)
            setupCurrTemp(currTemp)
            setupMinTemp(minTemp)
            setupMaxTemp(maxTemp)
            setupDetailButton()
        }
    }

    private fun setupDate(date: String) {
        view_day_forecast_date.text = date
    }

    private fun setupPicto(pictoUrl: String) {
        Glide
            .with(context)
            .load(pictoUrl)
            .into(view_day_forecast_picto)
    }

    private fun setupCurrTemp(temp: Double) {
        view_day_forecast_current_temp.text =
            resources.getString(R.string.temperature, temp.toString())
    }

    private fun setupMinTemp(temp: Double) {
        view_day_forecast_min_temp.text = resources.getString(R.string.temperature, temp.toString())
    }

    private fun setupMaxTemp(temp: Double) {
        view_day_forecast_max_temp.text = resources.getString(R.string.temperature, temp.toString())
    }

    private fun setupDetailButton() {
        view_day_forecast_details_button.apply {
            text = resources.getString(R.string.day_forecast_detail_button)
            setOnClickListener { listener?.onClickDetail() }
        }
    }
}