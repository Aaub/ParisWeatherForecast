package com.aaub.usecase.utils

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.reduceBy(nbrOfDecimals: Int): Double {
    val pow = 10F.pow(nbrOfDecimals) // 100
    val currentValPowed = this * pow //1911.0993829910010
    val currentValRounded = currentValPowed.roundToInt() // 1911
    val decimalsToDelete = currentValPowed - currentValRounded
    return (currentValPowed - decimalsToDelete) / pow
}

fun Double.reduce() = this.reduceBy(1)

