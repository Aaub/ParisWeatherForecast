package com.aaub.parisweatherforecast.utils

import android.content.Context
import com.aaub.parisweatherforecast.R
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.printDate(context: Context?): String {
    val date = SimpleDateFormat("dd MM yyyy", Locale.FRANCE)
        .apply {
            timeZone = this@printDate.timeZone
        }
        .format(this.time)
    val dayOfWeek = getDayOfTheWeek(context)
    return "$dayOfWeek $date"
}


fun Calendar.getDayOfTheWeek(context: Context?) = context?.resources?.getString(
    when (get(Calendar.DAY_OF_WEEK)) {
        1 -> R.string.sunday
        2 -> R.string.monday
        3 -> R.string.tuesday
        4 -> R.string.wednesday
        5 -> R.string.thursday
        6 -> R.string.friday
        7 -> R.string.saturday
        else -> R.string.no_day
    }
)