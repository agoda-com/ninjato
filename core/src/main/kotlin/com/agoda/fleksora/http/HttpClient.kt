package com.agoda.fleksora.http

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.log.Logger

interface HttpClient : Commons {
    var logger: Logger?
    var requestFactory: Request.Factory?
    var responseFactory: Response.Factory?

    fun execute(request: Request): Response

    companion object {
        fun build(instance: HttpClient, builder: HttpClient.() -> Unit) = instance.apply(builder)
    }
}
