package com.agoda.ninjato.sample

import com.agoda.ninjato.Api
import com.agoda.ninjato.converter.GsonBodyConverterFactory
import com.agoda.ninjato.sample.api.ForecastApi
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

class NinjatoApi : Api(), ForecastApi {
    override val baseUrl = "http://127.0.0.1:8080"

    override fun getForecast(request: ForecastRequest): ForecastResponse = post {
        endpointUrl = "/forecast"
        body = request
    }
}
