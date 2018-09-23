package com.basselezzeddine.worldweather.presentation.weather

import android.graphics.Bitmap

data class WeatherModel(val low: String,
                        val high: String,
                        val image: Bitmap?,
                        val current: String,
                        val visibility: String,
                        val pressure: String)