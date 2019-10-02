package com.aaub.parisweatherforecast.fivedays

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaub.parisweatherforecast.R
import com.aaub.parisweatherforecast.fivedays.dayforcastview.DayForecastView
import com.aaub.parisweatherforecast.fivedays.dayforcastview.DayForecastViewData

class FiveDaysAdapter(
    private val dayForecastViewDatas: List<DayForecastViewData>,
    private val listener: DayForecastView.Listener
) : RecyclerView.Adapter<FiveDaysAdapter.DayForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_five_days_adapter, parent, false)
        return DayForecastViewHolder(itemView)
    }

    override fun getItemCount(): Int = dayForecastViewDatas.size

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        holder.dayForecastView.apply {
            listener = this@FiveDaysAdapter.listener
            setupView(dayForecastViewDatas[position])
        }
    }


    inner class DayForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayForecastView: DayForecastView =
            view.findViewById(R.id.layout_five_days_adapter_day_forecast_view)
    }
}