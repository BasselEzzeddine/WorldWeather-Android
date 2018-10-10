package com.basselezzeddine.worldweather.presentation.cities

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.basselezzeddine.worldweather.utils.IntentKeys
import com.basselezzeddine.worldweather.R
import com.basselezzeddine.worldweather.utils.inflate
import com.basselezzeddine.worldweather.presentation.weather.WeatherActivity
import kotlinx.android.synthetic.main.recyclerview_item_city.view.*

class CitiesAdapter(private val cityList: List<City>) :
        RecyclerView.Adapter<CitiesAdapter.CityHolder>() {

    override fun getItemCount() = cityList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesAdapter.CityHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_city, false)
        return CityHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CitiesAdapter.CityHolder, position: Int) {
        val city = cityList[position]
        holder.bindCity(city)
    }

    class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var city: City

        init {
            itemView.setOnClickListener(this)
        }

        fun bindCity(city: City) {
            this.city = city
            itemView.textView_city.text = city.name
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, WeatherActivity::class.java)
            showPhotoIntent.putExtra(IntentKeys.city, city)
            context.startActivity(showPhotoIntent)
        }
    }
}