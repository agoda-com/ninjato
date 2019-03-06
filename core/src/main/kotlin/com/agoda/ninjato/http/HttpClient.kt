package com.agoda.ninjato.http

import com.agoda.ninjato.dsl.Commons
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.converter.ConverterFactories

abstract class HttpClient(
        protected var requestFactory: Request.Factory? = null,
        protected var responseFactory: Response.Factory? = null,
        receiver: (HttpClient.() -> Unit) = {}
) : Commons {

    init { receiver.invoke(this) }

    override val headers = Headers()
    override val interceptors = Interceptors()
    override val converterFactories = ConverterFactories()

    override var retryPolicy: RetryPolicy? = null
    override var fallbackPolicy: FallbackPolicy? = null

    abstract fun execute(request: Request): Response

    @PublishedApi
    internal fun request() = requestFactory?.create() ?: Request()

    operator fun invoke(receiver: HttpClient.() -> Unit) {
        apply(receiver)
    }
}
