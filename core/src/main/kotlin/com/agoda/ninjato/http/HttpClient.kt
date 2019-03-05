package com.agoda.ninjato.http

import com.agoda.ninjato.dsl.Commons
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.log.Logger
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.converter.ConverterFactories
import com.agoda.ninjato.log.Level

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

    operator fun invoke(receiver: HttpClient.() -> Unit) {
        apply(receiver)
    }

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
        fun <T: HttpClient> configure(instance: T, receiver: Configurator.(T) -> Unit): T {
            val configurator = Configurator()
            receiver.invoke(configurator, instance)
            return configurator.configure(instance) as T
        }
    }
}
