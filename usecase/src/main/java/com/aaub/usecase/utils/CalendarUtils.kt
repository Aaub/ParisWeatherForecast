package com.aaub.usecase.utils

import java.util.*

private const val MILLISECONDS_MULTPLIER = 1000

fun buildCalendar(timeInSeconds: Int) = GregorianCalendar()
    .apply {
        timeInMillis = timeInSeconds.toLong() * MILLISECONDS_MULTPLIER
    }

