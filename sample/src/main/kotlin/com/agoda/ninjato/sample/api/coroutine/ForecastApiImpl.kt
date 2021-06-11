package com.agoda.ninjato.sample.api.coroutine


import com.agoda.ninjato.Api
import com.agoda.ninjato.coroutine.CancellableApi
import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.extension.call.coroutine.call
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

class ForecastApiImpl(client: HttpClient, config: Api.() -> Unit = {}) : CancellableApi(client, config), ForecastApi {
    override val baseUrl = "https://ninjato.free.beeceptor.com"

    override suspend fun getForecast(request: ForecastRequest): Call<ForecastResponse> = call {
        post {
            endpointUrl = "/forecast"
            body = request
        }
    }
}
