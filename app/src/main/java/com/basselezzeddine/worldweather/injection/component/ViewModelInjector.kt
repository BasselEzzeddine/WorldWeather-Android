package com.basselezzeddine.worldweather.injection.component

import com.basselezzeddine.worldweather.injection.module.NetworkModule
import com.basselezzeddine.worldweather.presentation.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for ViewModels
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified ViewModel
     * @param weatherViewModel WeatherViewModel in which to inject the dependencies
     */
    fun inject(weatherViewModel: WeatherViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}