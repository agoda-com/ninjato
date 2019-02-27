package com.agoda.fleksora.intercept

import com.agoda.fleksora.http.Request

abstract class RequestInterceptor : Interceptor<Request>, Interceptor.Id {
    override fun hashCode() = id.hashCode()
    override fun equals(other: Any?) = id == (other as? RequestInterceptor)?.id
}
