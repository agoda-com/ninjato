package com.agoda.fleksora.http

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.converter.BodyConverter
import com.agoda.fleksora.converter.ConverterFactories

open class Request {
    lateinit var method: Method

    var baseUrl: String = ""
    var endpointUrl: String = ""
    var fullUrl: String = ""

    val url: String
        get() = if (fullUrl.isNotBlank()) fullUrl else baseUrl + endpointUrl

    val headers: MutableMap<String, MutableList<String>> = mutableMapOf()
    var body: Body? = null

    var retries = 0

    abstract class Factory {
        abstract fun create(): Request
    }

    open class Configurator : Commons {
        override val headers = Headers()
        override val interceptors = Interceptors()
        override val converterFactories = ConverterFactories()

        override var retryPolicy: RetryPolicy? = null
        override var fallbackPolicy: FallbackPolicy? = null

        var endpointUrl: String? = null
        var fullUrl: String? = null

        open fun configure(instance: Request) = instance.let {
            it.endpointUrl = endpointUrl ?: ""
            it.fullUrl = fullUrl ?: ""
            it.headers.putAll(headers.resolve())
            instance
        }

        class WithBody : Configurator() {
            var body: Any? by BodyConverter.Delegate(converterFactories)

            override fun configure(instance: Request) = super.configure(instance).let {
                it.body = body as Body?
                instance
            }
        }
    }
}
