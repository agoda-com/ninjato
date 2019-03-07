package com.agoda.ninjato.extensions.rxjava

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
class ApisTest {
    @Mock
    private lateinit var response: Response

    @Mock
    private lateinit var converterFactory: BodyConverter.Factory

    @Mock
    private lateinit var httpClient: HttpClient

    private lateinit var api: Api

    @Before
    fun setUp() {
        whenever(httpClient.execute(any())).thenReturn(response)
        api = TestApi(httpClient) {
            converterFactories += converterFactory
        }
    }

    @Test
    fun testCompletable() {
        api.completable { get<Response> { } }.test().assertCompleted()
    }

    @Test
    fun testSingle() {
        api.single { get<Response> { } }
                .test()
                .assertCompleted()
                .assertValue(response)
    }

    @Test
    fun testObservable() {
        api.observable { get<Response> { } }
                .test()
                .assertCompleted()
                .assertValue(response)
    }

    class TestApi(client: HttpClient, config: Api.() -> Unit = {}) : Api(client, config) {
        override val baseUrl = "http://127.0.0.1:8080"
    }
}
