package com.basselezzeddine.worldweather.presentation.weather

data class WeatherModel(val low: String,
                        val high: String,
                        val current: String,
                        val visibility: String,
                        val pressure: String,
                        val imageUrl: String)