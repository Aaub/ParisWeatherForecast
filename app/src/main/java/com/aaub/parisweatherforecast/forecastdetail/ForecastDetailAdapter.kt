package com.aaub.parisweatherforecast.forecastdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aaub.parisweatherforecast.R
import com.aaub.parisweatherforecast.forecastdetail.model.ForecastDetailViewData
import com.aaub.parisweatherforecast.ui.SeparatorTextView

class ForecastDetailAdapter(
    private val forecastDetailViewDatas: List<ForecastDetailViewData>
) : RecyclerView.Adapter<ForecastDetailAdapter.ForecastDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDetailViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_forecast_detail_adapter, parent, false)
        return ForecastDetailViewHolder(itemView)
    }

    override fun getItemCount(): Int = forecastDetailViewDatas.size

    override fun onBindViewHolder(holder: ForecastDetailViewHolder, position: Int) {
        val forecastDetailViewData = forecastDetailViewDatas[position]
        holder.apply {
            titleTextView.text = forecastDetailViewData.title
            worthTextView.text = forecastDetailViewData.worth
        }
    }


    inner class ForecastDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: SeparatorTextView =
            view.findViewById(R.id.layout_forecast_detail_adapter_title)
        val worthTextView: TextView = view.findViewById(R.id.layout_forecast_detail_adapter_worth)
    }
}