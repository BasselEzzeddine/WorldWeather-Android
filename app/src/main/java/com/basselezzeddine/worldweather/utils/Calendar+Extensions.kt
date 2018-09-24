package com.basselezzeddine.worldweather.utils

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.tomorrowString(): String {
    this.add(Calendar.DAY_OF_YEAR, 1)
    val tomorrow = this.time
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return dateFormat.format(tomorrow)
}