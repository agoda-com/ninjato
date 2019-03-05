package com.agoda.ninjato.sample.api

import com.agoda.ninjato.Api

class ForecastApiImpl : Api(), ForecastApi {
    override val baseUrl = "https://ninjato.free.beeceptor.com"
    override val api = this
}
