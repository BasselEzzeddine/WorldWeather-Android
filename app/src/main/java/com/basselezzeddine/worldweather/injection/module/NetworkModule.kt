package com.basselezzeddine.worldweather.injection.module

import com.basselezzeddine.worldweather.network.WeatherWorker
import com.basselezzeddine.worldweather.utils.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
object NetworkModule {

    /**
     * Provides the WeatherWorker implementation.
     * @param retrofit the Retrofit object used to instantiate the worker
     * @return the WeatherWorker implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideWeatherWorker(retrofit: Retrofit): WeatherWorker {
        return retrofit.create(WeatherWorker::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}