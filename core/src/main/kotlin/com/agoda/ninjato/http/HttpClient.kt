package com.agoda.ninjato.http

import com.agoda.ninjato.dsl.Commons
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.converter.ConverterFactories

abstract class HttpClient : Commons {
    override val headers = Headers()
    override val interceptors = Interceptors()
    override val converterFactories = ConverterFactories()

    override var retryPolicy: RetryPolicy? = null
    override var fallbackPolicy: FallbackPolicy? = null

    protected var requestFactory: Request.Factory? = null
    protected var responseFactory: Response.Factory? = null

    abstract fun execute(request: Request): Response

    @PublishedApi
    internal fun request() = requestFactory?.create() ?: Request()

    operator fun invoke(receiver: HttpClient.() -> Unit) {
        apply(receiver)
    }

    open class Configurator(private val client: HttpClient) : Commons by client {
        var requestFactory: Request.Factory? = null
        var responseFactory: Response.Factory? = null

        internal fun configure() = client.also {
            it.requestFactory = requestFactory
            it.responseFactory = responseFactory
        }
    }

    companion object {
        fun <T: HttpClient> configure(instance: T, receiver: Configurator.() -> Unit)
                = Configurator(instance).apply(receiver).configure() as T
    }
}
