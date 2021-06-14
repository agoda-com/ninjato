package com.agoda.ninjato.sample.api

import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse

interface ForecastApi {
    fun getForecast(request: ForecastRequest): ForecastResponse
    suspend fun getForecastAsync(request: ForecastRequest): ForecastResponse
}
