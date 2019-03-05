package com.agoda.ninjato.sample

import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApi {
    @POST("/forecast")
    fun getForecast(@Body request: ForecastRequest): Call<ForecastResponse>
}
