package com.agoda.fleksora.http

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.log.Logger
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.serial.SerializerFactories

abstract class HttpClient : Commons {
    override val headers = Headers()
    override val interceptors = Interceptors()
    override val serializerFactories = SerializerFactories()

    override var retryPolicy: RetryPolicy? = null
    override var fallbackPolicy: FallbackPolicy? = null

    protected var logger: Logger? = null
    protected var requestFactory: Request.Factory? = null
    protected var responseFactory: Response.Factory? = null

    abstract fun execute(request: Request): Response

    open class Builder {
        var logger: Logger? = null
        var requestFactory: Request.Factory? = null
        var responseFactory: Response.Factory? = null

        open fun build(instance: HttpClient) = instance.also {
            it.logger = logger
            it.requestFactory = requestFactory
            it.responseFactory = responseFactory
        }
    }

    companion object {
        fun build(instance: HttpClient, builder: HttpClient.Builder.() -> Unit) = Builder().apply(builder).build(instance)
    }
}
