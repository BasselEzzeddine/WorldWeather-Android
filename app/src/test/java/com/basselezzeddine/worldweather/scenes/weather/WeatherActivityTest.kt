package com.basselezzeddine.worldweather.scenes.weather

import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import com.basselezzeddine.worldweather.BuildConfig
import com.basselezzeddine.worldweather.R
import com.basselezzeddine.worldweather.scenes.IntentKeys
import com.basselezzeddine.worldweather.scenes.cities.City
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WeatherActivityTest {

    private var sut: WeatherActivity? = null

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(IntentKeys.city, City("Paris", "12345"))
        sut = Robolectric.buildActivity(WeatherActivity::class.java, intent).create().get()
    }

    @After
    fun tearDown() {
        sut = null
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(sut)
    }

    @Test
    fun whenViewLoads_displaysCorrectCity() {
        assertThat(sut?.title.toString(), equalTo("Paris"))
    }

    @Test
    fun callingDisplayWeatherInfo_displaysCorrectWeatherInfo_andHidesActivityIndicator() {
        // When
        val icon = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_sun)
        val viewModel = FetchSuccessViewModel("15°", "27°", icon, "19°", "10 km", "1000 hPa")
        sut?.displayWeatherInfo(viewModel)

        // Then
        assertThat(sut?.textViewLow?.text.toString(), equalTo("15°"))
        assertThat(sut?.textViewHigh?.text.toString(), equalTo("27°"))
        assertThat((sut?.imageViewWeather?.drawable as BitmapDrawable).bitmap, equalTo(icon))
        assertThat(sut?.textViewCurrent?.text.toString(), equalTo("19°"))
        assertThat(sut?.textViewVisibility?.text.toString(), equalTo("10 km"))
        assertThat(sut?.textViewPressure?.text.toString(), equalTo("1000 hPa"))
    }
}
