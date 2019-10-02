package com.aaub.parisweatherforecast.fivedays

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aaub.model.Detail
import com.aaub.parisweatherforecast.R
import com.aaub.parisweatherforecast.fivedays.dayforcastview.DayForecastView
import com.aaub.parisweatherforecast.fivedays.dayforcastview.DayForecastViewData
import com.aaub.parisweatherforecast.utils.getSnapPosition
import com.aaub.parisweatherforecast.utils.printDate
import com.aaub.parisweatherforecast.utils.viewModel
import kotlinx.android.synthetic.main.fragment_five_days.*

class FiveDaysFragment : Fragment(), DayForecastView.Listener {

    companion object {
        fun newInstance() = FiveDaysFragment()
    }

    private val viewModel by lazy {
        viewModel { FiveDaysViewModel() }
    }

    private var details = mutableListOf<Detail>()
    private val snapHelper by lazy { PagerSnapHelper() }
    private var listener: Listener? = null

    interface Listener {
        fun onClickDetail(detail: Detail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_five_days, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    private fun setupAdapter(dayForecastViewDatas: List<DayForecastViewData>) {
        fragment_five_days_recycler_view.apply {
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FiveDaysAdapter(dayForecastViewDatas, this@FiveDaysFragment)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.set(
                            Rect(
                                resources.getDimension(R.dimen.margin_double).toInt(),
                                0,
                                0,
                                0
                            )
                        )
                    }
                }
            })
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun observeViewModel() {
        viewModel.fiveDaysWeather.observe(this, Observer {
            setupAdapter(it.dayWeathers.map {
                with(it.givenPointWeathers.first()) {
                    details.add(detail)
                    DayForecastViewData(
                        date = this.date.printDate(this@FiveDaysFragment.context),
                        pictoUrl = image,
                        currTemp = tempCurrent,
                        minTemp = tempMin,
                        maxTemp = tempMax
                    )
                }
            })
        })
    }

    override fun onClickDetail() {
        listener?.onClickDetail(details[snapHelper.getSnapPosition(fragment_five_days_recycler_view)])
    }

}
