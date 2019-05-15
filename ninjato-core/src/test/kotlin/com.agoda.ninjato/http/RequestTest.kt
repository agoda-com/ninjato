package com.agoda.ninjato.http

import org.junit.Test

class RequestTest {

    private val configurator = Request.Configurator()

    @Test
    fun testConfigure() {
        // Arrange
        configurator.apply {
            endpointUrl = "/test"
            fullUrl = "https://127.0.0.1/fullTest"
            headers { "A" to "B" }
            parameters { "a" to "test param !"}
        }

        // Act
        val request = configurator.configure(Request())

        // Assert
        assert(request.endpointUrl == "/test")
        assert(request.url == "https://127.0.0.1/fullTest?a=test+param+%21")
        assert(request.headers["A"]!!.contains("B"))
    }

}
