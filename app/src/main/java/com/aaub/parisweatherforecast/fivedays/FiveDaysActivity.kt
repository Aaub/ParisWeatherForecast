package com.aaub.parisweatherforecast.fivedays

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aaub.model.Detail
import com.aaub.parisweatherforecast.R
import com.aaub.parisweatherforecast.utils.Intents


class FiveDaysActivity : AppCompatActivity(), FiveDaysFragment.Listener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_days)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_five_days_container, FiveDaysFragment.newInstance())
                .commitNow()
        }
    }


    override fun onClickDetail(detail: Detail) {
        startActivity(Intents.forecastDetail(this, detail))
    }
}
