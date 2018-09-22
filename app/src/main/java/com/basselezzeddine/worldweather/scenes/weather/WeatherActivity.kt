package com.basselezzeddine.worldweather.scenes.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.basselezzeddine.worldweather.scenes.IntentKeys
import com.basselezzeddine.worldweather.R
import com.basselezzeddine.worldweather.scenes.cities.City

class WeatherActivity : AppCompatActivity() {

    private var city: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
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
}
