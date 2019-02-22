package com.agoda.fleksora.dsl

import com.agoda.fleksora.http.Headers
import com.agoda.fleksora.http.Request
import com.agoda.fleksora.http.Response
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.intercept.RequestInterceptor
import com.agoda.fleksora.intercept.ResponseInterceptor
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.RetryPolicy

interface Commons {
    val headers: Headers
    val interceptors: Interceptors
    var retryPolicy: RetryPolicy?
    var fallbackPolicy: FallbackPolicy?

    fun requestInterceptor(interceptor: (Request) -> Request) {
        interceptors += object : RequestInterceptor() {
            override fun intercept(instance: Request) = interceptor(instance)
        }
    }

    fun responseInterceptor(interceptor: (Response) -> Response) {
        interceptors += object : ResponseInterceptor() {
            override fun intercept(instance: Response) = interceptor(instance)
        }
    }

    fun retryPolicy(policy: (Request, Throwable) -> Pair<Boolean, Long>) {
        retryPolicy = object : RetryPolicy() {
            override fun evaluate(request: Request, throwable: Throwable) = policy(request, throwable)
        }
    }

    fun fallbackPolicy(policy: (Request, Throwable) -> Request) {
        fallbackPolicy = object : FallbackPolicy() {
            override fun evaluate(request: Request, throwable: Throwable) = policy(request, throwable)
        }
    }
}
