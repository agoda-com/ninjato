package com.agoda.ninjato.extension.rxjava

import com.agoda.ninjato.Api
import com.agoda.ninjato.converter.BodyConverter
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
class RxExtensionsTest {
    @Mock
    private lateinit var converterFactory: BodyConverter.Factory

    @Mock
    private lateinit var httpClient: HttpClient

    private lateinit var api: Api

    private val response = Response().also { it.code = 200 }

    @Before
    fun setUp() {
        whenever(httpClient.execute(any())).thenReturn(response)

        api = TestApi(httpClient) {
            converterFactories += converterFactory
        }
    }

    @Test
    fun testCompletable() {
        api.completable { get { } }
                .test()
                .assertCompleted()
    }

    @Test
    fun testSingle() {
        api.single<Response> { delete { } }
                .test()
                .assertCompleted()
                .assertValue(response)
    }

    @Test
    fun testObservable() {
        api.observable<Response> { head { } }
                .test()
                .assertCompleted()
                .assertValue(response)
    }

    class TestApi(client: HttpClient, config: Api.() -> Unit = {}) : Api(client, config) {
        override val baseUrl = "http://127.0.0.1:8080"
    }
}
