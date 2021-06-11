package com.agoda.ninjato.coroutine

import com.agoda.ninjato.converter.BodyConverter
import com.agoda.ninjato.exception.HttpException
import com.agoda.ninjato.exception.MissingBodyException
import com.agoda.ninjato.exception.MissingConverterException
import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Response
import com.agoda.ninjato.policy.Retry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.reflect.Type

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ApiTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var client: HttpClient

    private lateinit var api: CancellableApi

    @Before
    fun setup() {
        api = object : CancellableApi(client) { override val baseUrl = "http://127.0.0.1:8080" }
    }

    @Test
    fun testGet() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 200 })

        // Act
        val response: Response = api.Get {
            endpointUrl = "/getResponse"

            headers {
                "A" to "B"
            }

            parameters += "a" to "test param"

            parameters {
                "b" to "c"
            }

            interceptors {
                request {
                    assert(it.endpointUrl == "/getResponse")
                    it
                }
            }

            interceptors {
                response {
                    assert(it.code == 200)
                    it
                }
            }
        }

        // Assert
        assert(response.request.endpointUrl == "/getResponse")
        assert(response.request.url == "http://127.0.0.1:8080/getResponse?a=test+param&b=c")
        assert(response.code == 200)
        assert(response.request.headers["A"] == listOf("B"))
        assert(response.isSuccess)
    }

    @Test
    fun testHead() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 200 })

        // Act
        api.Head<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testPost() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.Post {
            endpointUrl = "/getResponse?a=b"
            parameters += "b" to "c"
            body = TestRequest("testify_request")

            interceptors {
                request {
                    assert(it.url == "http://127.0.0.1:8080/getResponse?a=b&b=c")
                    it
                }
            }
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testPut() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.Put {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testDelete() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.Delete {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testOptions() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.Options { endpointUrl = "/getResponse" }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testPatch() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.Patch {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testRetryPolicy() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)
                coroutineTestRule.runBlockingTest {
                    whenever(client.execute(any())).thenReturn(Response().also {
                        it.code = 200
                        it.body = Body("testify_response")
                    })
                }
                Retry.WithoutDelay
            }
        }

        // Act
        val response: String = api.Get { endpointUrl = "/getResponse" }

        // Assert
        assert(response == "testify_response")
    }

    @Test(expected = HttpException::class)
    fun testRetryPolicyDoNotRetry() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)
                Retry.DoNotRetry
            }
        }

        // Act
        api.Get<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testRetryPolicyWithDelay() = coroutineTestRule.runBlockingTest {
        // Arrange
        val delay = mock<(() -> Unit)> {}

        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)
                coroutineTestRule.runBlockingTest {
                    whenever(client.execute(any())).thenReturn(Response().also {
                        it.code = 200
                        it.body = Body(byteArrayOf(1, 2, 3, 4, 5))
                    })
                }
                Retry.WithDelay(delay)
            }
        }

        // Act
        val response = api.Get<ByteArray> { endpointUrl = "/getResponse" }

        // Assert
        assert(response.contentEquals(byteArrayOf(1, 2, 3, 4, 5)))
        verify(delay).invoke()
    }

    @Test
    fun testFallbackPolicy() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)
                Retry.WithoutDelay
            }

            fallbackPolicy { request, throwable ->
                assert(throwable is HttpException)
                coroutineTestRule.runBlockingTest {
                    whenever(client.execute(any())).thenReturn(Response().also {
                        it.code = 200
                        it.body = Body("testify_response")
                    })
                }
                request.also { it.endpointUrl = "/fallbackedGetResponse" }
            }
        }

        // Act
        val response: Body = api.Get { endpointUrl = "/getResponse" }

        // Assert
        assert(response.asString == "testify_response")
    }

    @Test(expected = MissingBodyException::class)
    fun testMissingBodyException() = coroutineTestRule.runBlockingTest {
        // Act
        api.Post<TestResponse> {
            endpointUrl = "/getResponse"
        }
    }

    @Test(expected = MissingConverterException::class)
    fun testMissingConverterException() = coroutineTestRule.runBlockingTest {
        // Act
        api.Post<TestResponse> {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }
    }

    @Test(expected = HttpException::class)
    fun testHttpException() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        // Act
        api.Get<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testHttpExceptionNotThrown() = coroutineTestRule.runBlockingTest {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        // Act
        val response: Response = api.Get { endpointUrl = "/getResponse" }

        // Assert
        assert(response.code == 404)
        assert(!response.isSuccess)
    }

    data class TestRequest(val prop: String)
    data class TestResponse(val prop: String)

    class TestConverterFactory : BodyConverter.Factory {
        override fun requestConverter(type: Type): BodyConverter<*, Body>? {
            return object : BodyConverter<TestRequest, Body> {
                override fun convert(instance: TestRequest) = Body(instance.prop)
            }
        }

        override fun responseConverter(type: Type): BodyConverter<Body, *>? {
            return object : BodyConverter<Body, TestResponse> {
                override fun convert(instance: Body) = TestResponse(instance.asString)
            }
        }
    }
}
