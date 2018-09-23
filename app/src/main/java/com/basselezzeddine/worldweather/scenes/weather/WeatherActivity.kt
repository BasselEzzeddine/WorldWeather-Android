package com.basselezzeddine.worldweather.scenes.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.basselezzeddine.worldweather.scenes.IntentKeys
import com.basselezzeddine.worldweather.R
import com.basselezzeddine.worldweather.scenes.cities.City

interface WeatherActivityIn {
    fun displayWeatherInfo(viewModel: FetchSuccessViewModel)
}

interface WeatherActivityOut {
}

class WeatherActivity : AppCompatActivity(), WeatherActivityIn {

    @BindView(R.id.textView_low) lateinit var textViewLow: TextView
    @BindView(R.id.textView_high) lateinit var textViewHigh: TextView
    @BindView(R.id.imageView_weather) lateinit var imageViewWeather: ImageView
    @BindView(R.id.textView_current) lateinit var textViewCurrent: TextView
    @BindView(R.id.textView_visibility) lateinit var textViewVisibility: TextView
    @BindView(R.id.textView_pressure) lateinit var textViewPressure: TextView

    private var city: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        ButterKnife.bind(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        city = intent.extras.getSerializable(IntentKeys.city) as? City
        displayCity()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun displayCity() {
        title = city?.name
    }

    override fun displayWeatherInfo(viewModel: FetchSuccessViewModel) {
        textViewLow.text = viewModel.low
        textViewHigh.text = viewModel.high
        imageViewWeather.setImageBitmap(viewModel.image)
        textViewCurrent.text = viewModel.current
        textViewVisibility.text = viewModel.visibility
        textViewPressure.text = viewModel.pressure
    }
}
