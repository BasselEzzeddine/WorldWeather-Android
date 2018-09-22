package com.basselezzeddine.worldweather.scenes.cities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.basselezzeddine.worldweather.R
import kotlinx.android.synthetic.main.activity_cities.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.OrientationHelper.VERTICAL

class CitiesActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var citiesAdapter: CitiesAdapter
    private val cityList = listOf(
            City("Gothenburg", "890869"),
            City("Stockholm", "906057"),
            City("Mountain View", "2455920"),
            City("London", "44418"),
            City("New York", "2459115"),
            City("Berlin", "638242")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView_cities.layoutManager = linearLayoutManager
        citiesAdapter = CitiesAdapter(cityList)
        recyclerView_cities.adapter = citiesAdapter
        recyclerView_cities.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }
}
