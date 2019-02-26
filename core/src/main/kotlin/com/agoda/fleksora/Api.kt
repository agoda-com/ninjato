package com.agoda.fleksora

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.http.Headers
import com.agoda.fleksora.http.HttpClient
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.log.Logger
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.serial.SerializerFactories

abstract class Api : Commons {
    override val headers = Headers()
    override val interceptors = Interceptors()
    override val serializerFactories = SerializerFactories()

    override var retryPolicy: RetryPolicy? = null
    override var fallbackPolicy: FallbackPolicy? = null

    protected lateinit var client: HttpClient
    protected var logger: Logger? = null

    abstract val baseUrl: String
}
