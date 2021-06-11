package com.agoda.ninjato.sample.coroutine

import com.agoda.ninjato.Api
import com.agoda.ninjato.coroutine.CancellableApi
import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.extension.call.coroutine.call
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.sample.api.coroutine.ForecastApi
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

class NinjatoApi(client: HttpClient, config: Api.() -> Unit = {}) : CancellableApi(client, config), ForecastApi {
    override val baseUrl = "http://127.0.0.1:8080"

    override suspend fun getForecast(request: ForecastRequest): Call<ForecastResponse> = call {
        Post {
            endpointUrl = "/forecast"
            body = request
        }
    }
}
