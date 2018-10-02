package com.basselezzeddine.worldweather.presentation.weather

import android.content.Intent
import com.basselezzeddine.worldweather.BuildConfig
import com.basselezzeddine.worldweather.presentation.cities.City
import com.basselezzeddine.worldweather.utils.IntentKeys
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class WeatherActivityTest {

    private var sut: WeatherActivity? = null

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(IntentKeys.city, City("Paris", ""))
        sut = Robolectric.buildActivity(WeatherActivity::class.java, intent).create().get()
    }

    @After
    fun tearDown() {
        sut = null
    }

    @Test
    fun callingDisplayCity_displaysCorrectCity() {
        // When
        sut?.displayCity("Paris")

        // Then
        assertThat(sut?.title.toString(), equalTo("Paris"))
    }

    @Test
    fun callingDisplayWeatherInfo_displaysCorrectWeatherInfo() {
        // When
        val weatherModel = WeatherModel("15°", "27°", "19°", "10 km", "1000 hPa", "")
        sut?.displayWeatherInfo(weatherModel)

        // Then
        assertThat(sut?.textViewLow?.text.toString(), equalTo("15°"))
        assertThat(sut?.textViewHigh?.text.toString(), equalTo("27°"))
        assertThat(sut?.textViewCurrent?.text.toString(), equalTo("19°"))
        assertThat(sut?.textViewVisibility?.text.toString(), equalTo("10 km"))
        assertThat(sut?.textViewPressure?.text.toString(), equalTo("1000 hPa"))
    }

    @Test
    fun callingDisplayErrorMessage_displaysCorrectErrorMessage() {
        // When
        sut?.displayErrorMessage("My error message")

        // Then
        val alertDialog = ShadowAlertDialog.getLatestAlertDialog()
        assertThat(shadowOf(alertDialog).message.toString(), equalTo("My error message"))
    }
}