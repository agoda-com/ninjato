package com.agoda.fleksora.http

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.log.Logger
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.converter.ConverterFactories
import com.agoda.fleksora.log.Level

abstract class HttpClient : Commons {
    override val headers = Headers()
    override val interceptors = Interceptors()
    override val converterFactories = ConverterFactories()

    override var retryPolicy: RetryPolicy? = null
    override var fallbackPolicy: FallbackPolicy? = null

    protected var logger: Logger? = null
    protected var requestFactory: Request.Factory? = null
    protected var responseFactory: Response.Factory? = null

    abstract fun execute(request: Request): Response

    @PublishedApi
    internal fun request() = requestFactory?.create() ?: Request()

    open class Configurator {
        var logger: Logger? = null
        var requestFactory: Request.Factory? = null
        var responseFactory: Response.Factory? = null

        internal fun configure(instance: HttpClient) = instance.also {
            it.logger = logger
            it.requestFactory = requestFactory
            it.responseFactory = responseFactory

            logger?.log(
                    Level.Info,
                    "Configuring HttpClient -> $instance\n" +
                    "RequestFactory -> $requestFactory\n" +
                    "ResponseFactory -> $responseFactory"
            )
        }
    }

    companion object {
        fun configure(instance: HttpClient, configurator: HttpClient.Configurator.() -> Unit)
                = Configurator().apply(configurator).configure(instance)
    }
}
