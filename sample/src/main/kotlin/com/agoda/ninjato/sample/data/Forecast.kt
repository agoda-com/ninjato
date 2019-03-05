package com.agoda.ninjato.sample.data

data class Forecast(
        val day: Long,
        val city: String,
        val averageTemperature: Float,
        val maximumTemperature: Float,
        val minimumTemperature: Float
)
