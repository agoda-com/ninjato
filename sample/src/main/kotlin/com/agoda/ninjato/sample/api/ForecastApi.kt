package com.agoda.ninjato.sample.api

import com.agoda.ninjato.Api
import com.agoda.ninjato.sample.data.City
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

interface ForecastApi {
    val api: Api

    fun getForecast(start: Long, end: Long, cities: List<String>): ForecastResponse = api.post {
        endpointUrl = "/forecast"
        body = ForecastRequest(start, end, cities.map { City(it) })
    }
}
