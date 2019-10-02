package com.aaub.parisweatherforecast.forecastdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaub.model.Detail
import com.aaub.parisweatherforecast.R
import com.aaub.parisweatherforecast.forecastdetail.model.ForecastDetailViewData
import com.aaub.parisweatherforecast.utils.viewModel
import kotlinx.android.synthetic.main.fragment_forecast_detail.*

class ForecastDetailFragment : Fragment() {

    private var detail: Detail? = null
    private val viewModel by lazy {
        viewModel { ForecastDetailViewModel() }
    }

    companion object {
        const val DETAIL_KEY = "DETAIL_KEY"
        fun newInstance(detail: Detail?) = ForecastDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(DETAIL_KEY, detail)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detail = arguments?.getSerializable(DETAIL_KEY) as Detail?
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.buildDecomposedDetails(detail)
        viewModel.decomposedDetails.observe(this, Observer {
            setupAdapter(it.map {
                ForecastDetailViewData(
                    resources.getString(it.key.titleResId),
                    "${it.value} ${resources.getString(it.key.unitResId)}"
                )
            })
        })
    }


    private fun setupAdapter(forecastDetailViewDatas: List<ForecastDetailViewData>) {
        fragment_forecast_details_recycler_view.apply {
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = ForecastDetailAdapter(forecastDetailViewDatas)
        }
    }
}