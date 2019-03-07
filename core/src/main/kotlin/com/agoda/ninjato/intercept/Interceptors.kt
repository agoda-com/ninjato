package com.agoda.ninjato.intercept

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import java.util.*

/**
 * DSL context for aggregating [interceptors][Interceptor].
 * Supports additions of single and arrays of request and response interceptors.
 *
 * When resolving all the aggregations across the DSL cascade, keep in mind,
 * that interceptors that were defined on the lowest level will come first.
 */
class Interceptors {
    @PublishedApi
    internal var parent: Interceptors? = null

    private val request = LinkedList<RequestInterceptor>()
    private val response = LinkedList<ResponseInterceptor>()

    /**
     * Adds provided request interceptor to the aggregation.
     *
     * @param interceptor request interceptor to add
     */
    operator fun plusAssign(interceptor: RequestInterceptor) {
        request.add(interceptor)
    }

    /**
     * Adds provided request interceptors to the aggregation.
     *
     * @param interceptors request interceptors to add
     */
    operator fun plusAssign(interceptors: Array<out RequestInterceptor>) {
        request.addAll(interceptors)
    }

    /**
     * Adds provided response interceptor to the aggregation.
     *
     * @param interceptor response interceptor to add
     */
    operator fun plusAssign(interceptor: ResponseInterceptor) {
        response.add(interceptor)
    }

    /**
     * Adds provided response interceptors to the aggregation.
     *
     * @param interceptors response interceptors to add
     */
    operator fun plusAssign(interceptors: Array<out ResponseInterceptor>) {
        response.addAll(interceptors)
    }

    /**
     * Adds anonymous object of request interceptor to the aggregation.
     *
     * @param interceptor lambda with interception logic
     */
    fun request(interceptor: (Request) -> Request) {
        request.add(object : RequestInterceptor() {
            override fun intercept(instance: Request) = interceptor(instance)
        })
    }

    /**
     * Adds anonymous object of response interceptor to the aggregation.
     *
     * @param interceptor lambda with interception logic
     */
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
