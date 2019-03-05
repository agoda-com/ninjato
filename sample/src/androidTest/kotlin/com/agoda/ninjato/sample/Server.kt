package com.agoda.ninjato.sample

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

object Server {
    private var server: MockWebServer? = null

    fun start() {
        server = MockWebServer()

        server!!.enqueue(
                MockResponse()
                        .addHeader("Content-Type", "application/json;charset=utf-8")
                        .addHeader("Cache-Control", "no-cache")
                        .setBody("""
                            {
                              "response": [
                                {
                                  "day": 1551684044000,
                                  "city": "Bangkok",
                                  "average_temperature": 31.0,
                                  "maximum_temperature": 35.0,
                                  "minimum_temperature": 29.0
                                },
                                {
                                  "day": 1551770444000,
                                  "city": "Bangkok",
                                  "average_temperature": 30.0,
                                  "maximum_temperature": 34.0,
                                  "minimum_temperature": 28.0
                                },
                                {
                                  "day": 1551856844000,
                                  "city": "Bangkok",
                                  "average_temperature": 32.0,
                                  "maximum_temperature": 33.0,
                                  "minimum_temperature": 29.5
                                },
                                {
                                  "day": 1551943244000,
                                  "city": "Bangkok",
                                  "average_temperature": 35.1,
                                  "maximum_temperature": 38.3,
                                  "minimum_temperature": 34.4
                                },
                                {
                                  "day": 1552029644000,
                                  "city": "Bangkok",
                                  "average_temperature": 30.1,
                                  "maximum_temperature": 36.6,
                                  "minimum_temperature": 27.5
                                },
                                {
                                  "day": 1552116044000,
                                  "city": "Bangkok",
                                  "average_temperature": 32.3,
                                  "maximum_temperature": 33.3,
                                  "minimum_temperature": 27.8
                                },
                                {
                                  "day": 1552202444000,
                                  "city": "Bangkok",
                                  "average_temperature": 28.6,
                                  "maximum_temperature": 31.7,
                                  "minimum_temperature": 27.8
                                },
                                {
                                  "day": 1552288844000,
                                  "city": "Bangkok",
                                  "average_temperature": 30.9,
                                  "maximum_temperature": 34.3,
                                  "minimum_temperature": 28.5
                                },
                                {
                                  "day": 1552375244000,
                                  "city": "Bangkok",
                                  "average_temperature": 31.0,
                                  "maximum_temperature": 35.0,
                                  "minimum_temperature": 29.0
                                }
                              ]
                            }
                        """.trimIndent())
        )

        server!!.start(8080)
    }

    fun stop() {
        server!!.shutdown()
    }
}
