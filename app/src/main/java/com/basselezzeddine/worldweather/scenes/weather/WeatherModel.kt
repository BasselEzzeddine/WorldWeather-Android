package com.basselezzeddine.worldweather.scenes.weather

import android.graphics.Bitmap

data class FetchSuccessViewModel(val low: String,
                                 val high: String,
                                 val image: Bitmap?,
                                 val current: String,
                                 val visibility: String,
                                 val pressure: String)