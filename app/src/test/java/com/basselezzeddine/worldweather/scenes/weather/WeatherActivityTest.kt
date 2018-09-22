package com.basselezzeddine.worldweather.scenes.weather

import android.content.Intent
import com.basselezzeddine.worldweather.BuildConfig
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
}
