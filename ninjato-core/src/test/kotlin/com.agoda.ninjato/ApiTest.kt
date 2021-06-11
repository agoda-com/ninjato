package com.agoda.ninjato

import com.agoda.ninjato.converter.BodyConverter
import com.agoda.ninjato.exception.HttpException
import com.agoda.ninjato.exception.MissingBodyException
import com.agoda.ninjato.exception.MissingConverterException
import com.agoda.ninjato.http.Body
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Response
import com.agoda.ninjato.policy.Retry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
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
        val response: Response = api.get {
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
    fun testGetAsync() = runBlockingTest {
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also { it.code = 200 })

        // Act
        val response: Response = api.getAsync {
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
    fun testHead() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 200 })

        // Act
        api.head<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testHeadAsync() = runBlockingTest {
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also { it.code = 200 })

        // Act
        api.headAsync<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testPost() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.post {
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
    fun testPostAsync() = runBlockingTest {
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.postAsync {
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
    fun testPut() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.put {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testPutAsync() = runBlockingTest{
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.putAsync {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testDelete() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.delete {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testDeleteAsync() = runBlockingTest {
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.deleteAsync {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testOptions() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.options { endpointUrl = "/getResponse" }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testOptionsAsync() = runBlockingTest {
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.optionsAsync { endpointUrl = "/getResponse" }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testPatch() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.patch {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testPatchAsync() = runBlockingTest {
        // Arrange
        whenever(client.executeAsync(any())).thenReturn(Response().also {
            it.code = 200
            it.body = Body("testify_response")
        })

        api { converterFactories += TestConverterFactory() }

        // Act
        val response: TestResponse = api.patchAsync {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }

        // Assert
        assert(response.prop == "testify_response")
    }

    @Test
    fun testRetryPolicy() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)

                whenever(client.execute(any())).thenReturn(Response().also {
                    it.code = 200
                    it.body = Body("testify_response")
                })

                Retry.WithoutDelay
            }
        }

        // Act
        val response: String = api.get { endpointUrl = "/getResponse" }

        // Assert
        assert(response == "testify_response")
    }

    @Test(expected = HttpException::class)
    fun testRetryPolicyDoNotRetry() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)
                Retry.DoNotRetry
            }
        }

        // Act
        api.get<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testRetryPolicyWithDelay() {
        // Arrange
        val delay = mock<(() -> Unit)> {}

        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)

                whenever(client.execute(any())).thenReturn(Response().also {
                    it.code = 200
                    it.body = Body(byteArrayOf(1, 2, 3, 4, 5))
                })

                Retry.WithDelay(delay)
            }
        }

        // Act
        val response = api.get<ByteArray> { endpointUrl = "/getResponse" }

        // Assert
        assert(response.contentEquals(byteArrayOf(1, 2, 3, 4, 5)))
        verify(delay).invoke()
    }

    @Test
    fun testFallbackPolicy() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        api {
            retryPolicy { _, throwable ->
                assert(throwable is HttpException)
                Retry.WithoutDelay
            }

            fallbackPolicy { request, throwable ->
                assert(throwable is HttpException)

                whenever(client.execute(any())).thenReturn(Response().also {
                    it.code = 200
                    it.body = Body("testify_response")
                })

                request.also { it.endpointUrl = "/fallbackedGetResponse" }
            }
        }

        // Act
        val response: Body = api.get { endpointUrl = "/getResponse" }

        // Assert
        assert(response.asString == "testify_response")
    }

    @Test(expected = MissingBodyException::class)
    fun testMissingBodyException() {
        // Act
        api.post<TestResponse> {
            endpointUrl = "/getResponse"
        }
    }

    @Test(expected = MissingConverterException::class)
    fun testMissingConverterException() {
        // Act
        api.post<TestResponse> {
            endpointUrl = "/getResponse"
            body = TestRequest("testify_request")
        }
    }

    @Test(expected = HttpException::class)
    fun testHttpException() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        // Act
        api.get<Unit> { endpointUrl = "/getResponse" }
    }

    @Test
    fun testHttpExceptionNotThrown() {
        // Arrange
        whenever(client.execute(any())).thenReturn(Response().also { it.code = 404 })

        // Act
        val response: Response = api.get { endpointUrl = "/getResponse" }

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
