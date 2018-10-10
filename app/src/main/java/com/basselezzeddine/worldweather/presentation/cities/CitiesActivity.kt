package com.basselezzeddine.worldweather.presentation.cities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper.VERTICAL
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.basselezzeddine.worldweather.R

class CitiesActivity : AppCompatActivity() {

    @BindView(R.id.recyclerView_cities)
    lateinit var recyclerViewCities: RecyclerView

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
        ButterKnife.bind(this)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewCities.layoutManager = linearLayoutManager
        citiesAdapter = CitiesAdapter(cityList)
        recyclerViewCities.adapter = citiesAdapter
        recyclerViewCities.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }
}