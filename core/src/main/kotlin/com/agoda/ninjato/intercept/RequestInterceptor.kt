package com.agoda.ninjato.intercept

import com.agoda.ninjato.http.Request

/**
 * Base class for [request][Request] interceptors.
 */
abstract class RequestInterceptor : Interceptor<Request> {
    override lateinit var id: String

    override fun hashCode() = id.hashCode()
    override fun equals(other: Any?) = id == (other as? RequestInterceptor)?.id
}
