package com.agoda.ninjato.sample

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agoda.ninjato.Api.Companion.configure
import com.agoda.ninjato.sample.api.ForecastApi
import com.agoda.ninjato.sample.api.ForecastApiImpl
import com.agoda.ninjato.client.NinjatoOkHttpClient
import com.agoda.ninjato.converter.GsonBodyConverterFactory
import com.agoda.ninjato.sample.data.Forecast
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient

class NinjatoActivity : Activity() {

    val recycler by lazy { findViewById<RecyclerView>(R.id.recycler) }

    val api: ForecastApi = configure(ForecastApiImpl()) {
        httpClient = NinjatoOkHttpClient(OkHttpClient())

        converterFactories += GsonBodyConverterFactory(
                GsonBuilder()
                        .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
        )
    }

    val job = Job()
    val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ninjato)
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

        scope.launch {
            val forecast = async(Dispatchers.IO) { api.getForecast(100, 200, listOf("Bangkok")) }
            recycler.adapter = ForecastAdapter(forecast.await().response)
        }
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    class ForecastAdapter(private val items: List<Forecast>) : RecyclerView.Adapter<ForecastViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                = ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false))

        override fun getItemCount() = items.size
        override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.bind(items[position])
    }

    class ForecastViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val date: TextView = item.findViewById(R.id.date_text)
        private val city: TextView = item.findViewById(R.id.city_text)
        private val averageTemp : TextView = item.findViewById(R.id.average_temp_text)
        private val maxTemp: TextView = item.findViewById(R.id.max_temp_text)
        private val minTemp: TextView = item.findViewById(R.id.min_temp_text)

        fun bind(forecast: Forecast) {
            forecast.let {
                date.text = it.day.toString()
                city.text = it.city
                averageTemp.text = it.averageTemperature.toString()
                maxTemp.text = it.maximumTemperature.toString()
                minTemp.text = it.minimumTemperature.toString()
            }
        }
    }
}
