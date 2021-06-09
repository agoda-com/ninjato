package com.agoda.ninjato.sample.api

import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

interface ForecastApi {
    fun getForecast(request: ForecastRequest): ForecastResponse
    fun getForecastAsCall(request: ForecastRequest): Call<ForecastResponse>
}
