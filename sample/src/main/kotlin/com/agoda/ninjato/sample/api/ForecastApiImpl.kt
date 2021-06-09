package com.agoda.ninjato.sample.api

import com.agoda.ninjato.Api
import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.extension.call.call
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

class ForecastApiImpl(client: HttpClient, config: Api.() -> Unit = {}) : Api(client, config), ForecastApi {
    override val baseUrl = "https://ninjato.free.beeceptor.com"

    override fun getForecast(request: ForecastRequest): ForecastResponse = post {
        endpointUrl = "/forecast"
        body = request
    }

    override fun getForecastAsCall(request: ForecastRequest): Call<ForecastResponse> = call {
        post {
            endpointUrl = "/forecast"
            body = request
        }
    }
}
