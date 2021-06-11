package com.agoda.ninjato.extension.call

import com.agoda.ninjato.Api
import com.agoda.ninjato.exception.HttpException
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CallExtensionsTest {
    @Mock
    private lateinit var httpClient: HttpClient

    private lateinit var api: Api

    private val dispatcher = TestCoroutineDispatcher()
    private val response = Response().also { it.code = 200 }

    @Before
    fun setup() {
        whenever(httpClient.execute(any())).thenReturn(response)
        
        runBlockingTest(dispatcher) {
            whenever(httpClient.executeAsync(any())).thenReturn(response)
        }
        
        api = TestApi(httpClient)
    }

    @Test
    fun testSuccess() {
        val result = api.call<Response> {
            get {}
        }

        assert(result is Call.Success)
        assert((result as Call.Success).result == response)
    }

    @Test
    fun testFailure() {
        response.code = 404

        val result = api.call<String> {
            get {}
        }

        assert(result is Call.Failure)
        assert((result as Call.Failure).throwable is HttpException)
    }
    
    @Test
    fun testAsyncSuccess() = runBlockingTest(dispatcher) {
        val result = api.callAsync<Response> { 
            getAsync {}
        }

        assert(result is Call.Success)
        assert((result as Call.Success).result == response)
    }
    
    @Test
    fun testAsyncFailure() = runBlockingTest(dispatcher) {
        response.code = 404

        val result = api.call<String> {
            get {}
        }

        assert(result is Call.Failure)
        assert((result as Call.Failure).throwable is HttpException)
    }
    
    @Test
    fun testHttpExceptionIsNotThrownForResponse() {
        response.code = 404

        val result = api.call<Response> {
            get {}
        }

        assert(result is Call.Success)
        assert((result as Call.Success).result.code == 404)
    }

    class TestApi(client: HttpClient, config: Api.() -> Unit = {}) : Api(client, config) {
        override val baseUrl = "http://127.0.0.1:8080"
    }
}
