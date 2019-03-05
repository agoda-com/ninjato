package com.agoda.ninjato.sample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.ninjato.sample.data.City
import com.agoda.ninjato.sample.data.ForecastRequest
import com.agoda.ninjato.sample.repeat.Repeat
import com.agoda.ninjato.sample.repeat.RepeatRule
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4ClassRunner::class)
class RetrofitTest {

    @Rule
    @JvmField
    val rule = RepeatRule()

    private val api = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080")
            .client(OkHttpClient())
            .addConverterFactory(
                    GsonConverterFactory.create(
                            GsonBuilder()
                                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                    .create()
                    )
            )
            .build()
            .create(RetrofitApi::class.java)

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
        val result = api.getForecast(ForecastRequest(100, 200, listOf(City("Bangkok")))).execute()
        assert(result.body()!!.response.size == 9)
    }
}
