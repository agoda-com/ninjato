package com.agoda.ninjato.sample.api

import com.agoda.ninjato.Api
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

class ForecastApiImpl : Api(), ForecastApi {
    override val baseUrl = "https://ninjato.free.beeceptor.com"

    override fun getForecast(request: ForecastRequest): ForecastResponse = post {
        endpointUrl = "/forecast"
        body = request
    }
}
