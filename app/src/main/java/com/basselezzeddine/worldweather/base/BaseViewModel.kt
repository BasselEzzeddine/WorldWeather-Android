package com.basselezzeddine.worldweather.base

import android.arch.lifecycle.ViewModel
import com.basselezzeddine.worldweather.injection.component.DaggerViewModelInjector
import com.basselezzeddine.worldweather.injection.component.ViewModelInjector
import com.basselezzeddine.worldweather.injection.module.NetworkModule
import com.basselezzeddine.worldweather.presentation.weather.WeatherViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is WeatherViewModel -> injector.inject(this)
        }
    }
}