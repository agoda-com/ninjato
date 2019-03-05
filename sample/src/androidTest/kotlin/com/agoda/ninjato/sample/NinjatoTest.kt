package com.agoda.ninjato.sample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.ninjato.Api
import com.agoda.ninjato.client.NinjatoOkHttpClient
import com.agoda.ninjato.sample.repeat.Repeat
import com.agoda.ninjato.sample.repeat.RepeatRule
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

    private val api = Api.configure(NinjatoApi()) {
        httpClient = NinjatoOkHttpClient(OkHttpClient())
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
    fun testPost() {
        val result = api.getForecast(100, 200, listOf("Bangkok"))
        assert(result.response.size == 9)
    }

}
