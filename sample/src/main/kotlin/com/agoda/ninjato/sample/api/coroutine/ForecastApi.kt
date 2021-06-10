package com.agoda.ninjato.sample.api.coroutine

import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

interface ForecastApi {
    suspend fun getForecast(request: ForecastRequest): Call<ForecastResponse>
}
