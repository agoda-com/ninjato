package com.agoda.fleksora.intercept

import com.agoda.fleksora.http.Response

abstract class ResponseInterceptor : Interceptor<Response>, Interceptor.Id {
    override fun hashCode() = id.hashCode()
    override fun equals(other: Any?) = id == (other as? RequestInterceptor)?.id
}
