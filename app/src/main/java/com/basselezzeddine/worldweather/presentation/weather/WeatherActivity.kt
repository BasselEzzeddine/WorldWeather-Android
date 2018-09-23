package com.basselezzeddine.worldweather.presentation.weather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View.GONE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.basselezzeddine.worldweather.R
import com.basselezzeddine.worldweather.presentation.cities.City
import com.basselezzeddine.worldweather.utils.IntentKeys

class WeatherActivity : AppCompatActivity() {

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.textView_low)
    lateinit var textViewLow: TextView

    @BindView(R.id.textView_high)
    lateinit var textViewHigh: TextView

    @BindView(R.id.imageView_weather)
    lateinit var imageViewWeather: ImageView

    @BindView(R.id.textView_current)
    lateinit var textViewCurrent: TextView

    @BindView(R.id.textView_visibility)
    lateinit var textViewVisibility: TextView

    @BindView(R.id.textView_pressure)
    lateinit var textViewPressure: TextView

    private lateinit var viewModel: WeatherViewModel
    private var city: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        ButterKnife.bind(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        city = intent.extras.getSerializable(IntentKeys.city) as? City
        setupViewModel()
        observeWeatherModel()
        observeErrorMessage()
        displayCity(city?.name ?: "")
        viewModel.fetchWeatherInfo(city?.woeid ?: "")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    private fun observeWeatherModel() {
        viewModel.weatherModel.observe(this, Observer { weatherModel: WeatherModel? ->
            if (weatherModel != null) {
                displayWeatherInfo(weatherModel)
            }
        })
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(this, Observer { errorMessage: Int? ->
            if (errorMessage != null) {
                displayErrorMessage(getString(errorMessage))
            }
        })
    }

    fun displayCity(cityName: String) {
        title = cityName
    }

    fun displayWeatherInfo(weatherModel: WeatherModel) {
        progressBar.visibility = GONE
        textViewLow.text = weatherModel.low
        textViewHigh.text = weatherModel.high
        imageViewWeather.setImageBitmap(weatherModel.image)
        textViewCurrent.text = weatherModel.current
        textViewVisibility.text = weatherModel.visibility
        textViewPressure.text = weatherModel.pressure
    }

    fun displayErrorMessage(message: String) {
        progressBar.visibility = GONE
        val builder = AlertDialog.Builder(this@WeatherActivity)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            finish()
        }
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
    }
}