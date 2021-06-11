package com.agoda.ninjato.sample

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agoda.ninjato.sample.api.ForecastApi
import com.agoda.ninjato.sample.api.ForecastApiImpl
import com.agoda.ninjato.client.NinjatoOkHttpClient
import com.agoda.ninjato.converter.GsonBodyConverterFactory
import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.sample.data.City
import com.agoda.ninjato.sample.data.Forecast
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import com.agoda.ninjato.sample.api.coroutine.ForecastApi as CoroutineApi
import com.agoda.ninjato.sample.api.coroutine.ForecastApiImpl as CoroutineApiImpl

class NinjatoActivity : Activity() {
    private val recycler by lazy { findViewById<RecyclerView>(R.id.recycler) }
    private val btnCoroutine by lazy { findViewById<Button>(R.id.btn_coroutine) }

    private val ninjatoOkHttpClient = NinjatoOkHttpClient(OkHttpClient())
    private val api: ForecastApi = ForecastApiImpl(ninjatoOkHttpClient) {
        converterFactories += GsonBodyConverterFactory(
                GsonBuilder()
                        .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
        )
    }

    private val coroutineApi: CoroutineApi = CoroutineApiImpl(ninjatoOkHttpClient) {
        converterFactories += GsonBodyConverterFactory(
            GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        )
    }

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private var blockingJob: Job? = null
    private var asyncJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ninjato)
        recycler.layoutManager = LinearLayoutManager(this)
        btnCoroutine.setOnClickListener {
            callCoroutine()
        }
    }

    private fun callCoroutine() {
        if (blockingJob?.isActive == true) {
            Toast.makeText(this, "Please wait a moment", Toast.LENGTH_SHORT).show()
            return
        }
        asyncJob?.cancel()
        asyncJob = scope.launch {
            val forecast = withContext(Dispatchers.IO) {
                coroutineApi.getForecast(ForecastRequest(100, 200, listOf(City("Bangkok"))))
            }
            when (forecast) {
                is Call.Success<ForecastResponse> -> {
                    (recycler.adapter as? ForecastAdapter)?.updateData(forecast.result.response)
                }
                else -> Toast.makeText(this@NinjatoActivity, "Error fetching forecast", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        blockingJob = scope.launch {
            val forecast = async(Dispatchers.IO) { api.getForecast(ForecastRequest(100, 200, listOf(City("Bangkok")))) }
            recycler.adapter = ForecastAdapter(forecast.await().response)
        }
    }

    override fun onStop() {
        super.onStop()
        scope.cancel()
    }

    class ForecastAdapter(items: List<Forecast>) : RecyclerView.Adapter<ForecastViewHolder>() {
        private var mData: List<Forecast> = items
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                = ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false))

        override fun getItemCount() = mData.size
        override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.bind(mData[position])

        fun updateData(items: List<Forecast>) {
            mData = items
            notifyDataSetChanged()
        }
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
