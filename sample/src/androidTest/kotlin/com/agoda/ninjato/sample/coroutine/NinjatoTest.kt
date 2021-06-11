package com.agoda.ninjato.sample.coroutine

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.ninjato.client.NinjatoOkHttpClient
import com.agoda.ninjato.converter.GsonBodyConverterFactory
import com.agoda.ninjato.extension.call.Call
import com.agoda.ninjato.sample.Server
import com.agoda.ninjato.sample.data.City
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.data.ForecastResponse
import com.agoda.ninjato.sample.repeat.Repeat
import com.agoda.ninjato.sample.repeat.RepeatRule
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NinjatoTest {

    @Rule
    @JvmField
    val rule = RepeatRule()

    private val api = NinjatoApi(NinjatoOkHttpClient(OkHttpClient())) {
        converterFactories += GsonBodyConverterFactory(
                GsonBuilder()
                        .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
        )
    }

    @Before
    fun setup() {
        Server.start()
    }

    @After
    fun cleanup() {
        Server.stop()
    }

    @Test
    @Repeat(100)
    fun testPost() = runBlocking {
        val result = api.getForecast(ForecastRequest(100, 200, listOf(City("Bangkok"))))
        assert((result as Call.Success<ForecastResponse>).result.response.size == 9)
    }
}
