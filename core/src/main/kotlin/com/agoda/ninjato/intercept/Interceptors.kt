package com.agoda.ninjato.intercept

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import java.util.*

class Interceptors {
    @PublishedApi
    internal var parent: Interceptors? = null

    private val request = LinkedList<RequestInterceptor>()
    private val response = LinkedList<ResponseInterceptor>()

    operator fun plusAssign(interceptor: RequestInterceptor) {
        request.add(interceptor)
    }

    operator fun plusAssign(interceptors: Array<out RequestInterceptor>) {
        request.addAll(interceptors)
    }

    operator fun plusAssign(interceptor: ResponseInterceptor) {
        response.add(interceptor)
    }

    operator fun plusAssign(interceptors: Array<out ResponseInterceptor>) {
        response.addAll(interceptors)
    }

    fun request(interceptor: (Request) -> Request) {
        request.add(object : RequestInterceptor() {
            override fun intercept(instance: Request) = interceptor(instance)
        })
    }

    fun response(interceptor: (Response) -> Response) {
        response.add(object : ResponseInterceptor() {
            override fun intercept(instance: Response) = interceptor(instance)
        })
    }

    operator fun invoke(receiver: Interceptors.() -> Unit) {
        this.apply(receiver)
    }

    @PublishedApi
    internal fun resolveRequest(): MutableList<RequestInterceptor>
            = parent?.resolveRequest()?.also { it.addAll(0, request) } ?: request

    @PublishedApi
    internal fun resolveResponse(): MutableList<ResponseInterceptor>
            = parent?.resolveResponse()?.also { it.addAll(0, response) } ?: response
}
