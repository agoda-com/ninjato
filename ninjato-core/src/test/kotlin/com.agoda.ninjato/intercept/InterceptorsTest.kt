package com.agoda.ninjato.intercept

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import org.junit.Test

class InterceptorsTest {

    private val interceptors = Interceptors()

    private val requestFirst = object : RequestInterceptor() {
        override fun intercept(instance: Request) = instance
    }

    private val requestSecond = object : RequestInterceptor() {
        override fun intercept(instance: Request) = instance
    }

    private val responseFirst = object : ResponseInterceptor() {
        override fun intercept(instance: Response) = instance
    }

    private val responseSecond = object : ResponseInterceptor() {
        override fun intercept(instance: Response) = instance
    }

    @Test
    fun testResolveRequest() {
        // Arrange
        interceptors += requestFirst
        interceptors += arrayOf(requestSecond)
        interceptors { request { it } }

        // Act
        val result = interceptors.resolveRequest()

        // Assert
        assert(result[0] == requestFirst)
        assert(result[1] == requestSecond)
        assert(result.size == 3)
    }

    @Test
    fun testResolveResponse() {
        // Arrange
        interceptors += responseFirst
        interceptors += arrayOf(responseSecond)
        interceptors { response { it } }

        // Act
        val result = interceptors.resolveResponse()

        // Assert
        assert(result[0] == responseFirst)
        assert(result[1] == responseSecond)
        assert(result.size == 3)
    }

    @Test
    fun testResolveRequestWithParent() {
        // Arrange
        val parent = Interceptors()

        parent += requestFirst
        interceptors += requestSecond

        interceptors.parent = parent

        // Act
        val result = interceptors.resolveRequest()

        // Assert
        assert(result[0] == requestSecond)
        assert(result[1] == requestFirst)
    }

    @Test
    fun testResolveResponseWithParent() {
        // Arrange
        val parent = Interceptors()

        parent += responseFirst
        interceptors += responseSecond

        interceptors.parent = parent

        // Act
        val result = interceptors.resolveResponse()

        // Assert
        assert(result[0] == responseSecond)
        assert(result[1] == responseFirst)
    }

}
