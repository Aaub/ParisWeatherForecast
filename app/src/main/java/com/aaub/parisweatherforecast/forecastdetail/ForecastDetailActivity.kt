package com.aaub.parisweatherforecast.forecastdetail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.aaub.model.Detail
import com.aaub.parisweatherforecast.R
import com.aaub.parisweatherforecast.utils.Intents
import kotlinx.android.synthetic.main.activity_forecast_detail.*


class ForecastDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_detail)

        val detail = intent.getSerializableExtra(Intents.INTENT_EXTRA_FORECAST_DETAIL) as Detail?
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.activity_forecast_detail_container,
                    ForecastDetailFragment.newInstance(detail)
                )
                .commitNow()
        }
        setToolbar()
    }

    private fun setToolbar() {
        activity_forecast_detail_toolbar.also {
            it.title = resources.getString(R.string.activity_forecast_detail)
            it.navigationIcon =
                ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back, null)
            setSupportActionBar(it)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
