package com.agoda.ninjato.sample.data

data class ForecastRequest(
        val startDate: Long,
        val endDate: Long,
        val cities: List<City>
)
