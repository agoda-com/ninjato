package com.agoda.ninjato

import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Response
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiTest {

    @Mock
    lateinit var client: HttpClient

    private lateinit var api: Api

    @Before
    fun setup() {
        api = object : Api(client) { override val baseUrl = "http://127.0.0.1:8080" }
    }

    @Test
    fun testGet() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 200 })

        // Act
        val response: Response = api.get { endpointUrl = "/getResponse" }

        // Assert
        assert(response.request.endpointUrl == "/getResponse")
        assert(response.request.url == "http://127.0.0.1:8080/getResponse")
        assert(response.code == 200)
        assert(response.isSuccess)
    }

}
