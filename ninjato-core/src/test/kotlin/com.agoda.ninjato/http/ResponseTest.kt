package com.agoda.ninjato.http

import org.junit.Test

class ResponseTest {

    private val response = Response()

    @Test
    fun testIsSuccess() {
        // Arrange
        response.code = 200

        // Assert
        assert(response.isSuccess)
    }

    @Test
    fun testIsFailure() {
        // Arrange
        response.code = 404

        // Assert
        assert(!response.isSuccess)
    }
}
