package com.basselezzeddine.worldweather.network

import com.basselezzeddine.worldweather.model.RawWeatherModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherWorker {

    /**
     * Get RawWeatherModel from the API
     */
    @GET("/api/location/{woeid}")
    fun fetchWeatherInfo(@Path(value = "woeid", encoded = true) woeid: String): Observable<RawWeatherModel>
}