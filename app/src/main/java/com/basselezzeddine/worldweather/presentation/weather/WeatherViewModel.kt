package com.basselezzeddine.worldweather.presentation.weather

import android.arch.lifecycle.MutableLiveData
import com.basselezzeddine.worldweather.R
import com.basselezzeddine.worldweather.base.BaseViewModel
import com.basselezzeddine.worldweather.model.RawWeatherModel
import com.basselezzeddine.worldweather.network.WeatherWorker
import com.basselezzeddine.worldweather.utils.WEATHER_BASE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel : BaseViewModel() {

    @Inject
    lateinit var weatherWorker: WeatherWorker

    private lateinit var subscription: Disposable
    val weatherModel = MutableLiveData<WeatherModel>()
    val errorMessage = MutableLiveData<Int>()

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun fetchWeatherInfo(cityWoeid: String) {
        if (cityWoeid != "") {
            subscription = weatherWorker.fetchWeatherInfo(cityWoeid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrieveWeatherInfoStart() }
                    .doOnTerminate { onRetrieveWeatherInfoFinish() }
                    .subscribe(
                            { rawWeatherInfo -> onRetrieveWeatherInfoSuccess(rawWeatherInfo) },
                            { error -> onRetrieveWeatherInfoError(error) }
                    )
        }
    }

    private fun onRetrieveWeatherInfoStart() {
        weatherModel.value = null
        errorMessage.value = null
    }

    private fun onRetrieveWeatherInfoFinish() {
    }

    private fun onRetrieveWeatherInfoSuccess(rawWeatherInfo: RawWeatherModel) {
        val tomorrowWeather = rawWeatherInfo.consolidated_weather.first()
        val low = "%.0f°".format(tomorrowWeather.min_temp)
        val high = "%.0f°".format(tomorrowWeather.max_temp)
        val current = "%.0f°".format(tomorrowWeather.the_temp)
        val visibility = "%.0f km".format(tomorrowWeather.visibility)
        val pressure = "%.0f hPa".format(tomorrowWeather.air_pressure)

        val iconName = tomorrowWeather.weather_state_abbr
        val imageUrl = "$WEATHER_BASE_URL/static/img/weather/png/64/$iconName.png"

        weatherModel.value = WeatherModel(low, high, current, visibility, pressure, imageUrl)
    }

    private fun onRetrieveWeatherInfoError(error: Throwable) {
        print(error)
        errorMessage.value = R.string.fetchWeatherInfoErrorMessage
    }
}