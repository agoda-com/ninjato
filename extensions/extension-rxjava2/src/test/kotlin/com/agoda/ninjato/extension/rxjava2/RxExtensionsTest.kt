package com.agoda.ninjato.extension.rxjava2

import com.agoda.ninjato.Api
import com.agoda.ninjato.http.HttpClient
import com.agoda.ninjato.http.Response
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RxExtensionsTest {
    @Mock
    private lateinit var httpClient: HttpClient

    private lateinit var api: Api

    private val response = Response().also { it.code = 200 }

    @Before
    fun setUp() {
        whenever(httpClient.execute(any())).thenReturn(response)
        api = TestApi(httpClient)
    }

    @Test
    fun testCompletable() {
        api.completable { get { } }.test().assertComplete()
    }

    @Test
    fun testSingle() {
        api.single<Response> { head { } }
                .test()
                .assertComplete()
                .assertValue(response)
    }

    @Test
    fun testObservable() {
        api.observable<Response> { delete { } }
                .test()
                .assertComplete()
                .assertValue(response)
    }

    @Test
    fun testFlowable() {
        api.flowable<Response> { post { body = "test" } }
                .test()
                .assertComplete()
                .assertValue(response)
    }

    class TestApi(client: HttpClient, config: Api.() -> Unit = {}) : Api(client, config) {
        override val baseUrl = "http://127.0.0.1:8080"
    }
}
