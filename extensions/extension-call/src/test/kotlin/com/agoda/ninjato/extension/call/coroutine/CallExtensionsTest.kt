package com.agoda.ninjato.extension.call.coroutine

import com.agoda.ninjato.Api
import com.agoda.ninjato.coroutine.CancellableApi
import com.agoda.ninjato.exception.HttpException
import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CallExtensionsTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var httpClient: HttpClient

    private lateinit var api: CancellableApi

    private val response = Response().also { it.code = 200 }

    @Before
    fun setup() = coroutineTestRule.runBlockingTest {
        whenever(httpClient.execute(any())).thenReturn(response)
        api = TestApi(httpClient)
    }

    @Test
    fun testSuccess() = coroutineTestRule.runBlockingTest {
        val result = api.call<Response> {
            get {}
        }

        assert(result is Call.Success)
        assert((result as Call.Success).result == response)
    }

    @Test
    fun testFailure() = coroutineTestRule.runBlockingTest {
        response.code = 404

        val result = api.call<String> {
            get {}
        }

        assert(result is Call.Failure)
        assert((result as Call.Failure).throwable is HttpException)
    }

    @Test
    fun testHttpExceptionIsNotThrownForResponse() = coroutineTestRule.runBlockingTest {
        response.code = 404

        val result = api.call<Response> {
            get {}
        }

        assert(result is Call.Success)
        assert((result as Call.Success).result.code == 404)
    }

    class TestApi(client: HttpClient, config: Api.() -> Unit = {}) : CancellableApi(client, config) {
        override val baseUrl = "http://127.0.0.1:8080"
    }
}
