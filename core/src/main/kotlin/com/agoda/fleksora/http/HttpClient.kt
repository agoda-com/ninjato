package com.agoda.fleksora.http

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.log.Logger

abstract class HttpClient : Commons {
    internal var logger: Logger? = null
    internal var requestFactory: Request.Factory? = null
    internal var responseFactory: Response.Factory? = null

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
