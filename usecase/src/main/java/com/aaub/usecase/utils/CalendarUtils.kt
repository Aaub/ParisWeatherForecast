package com.aaub.usecase.utils

import java.util.*

fun getDayOfYear(timeInMilli: Int) = Calendar
    .getInstance()
    .apply {
        timeInMillis = timeInMilli.toLong()
    }
    .get(Calendar.DAY_OF_YEAR)

