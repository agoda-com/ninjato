package com.agoda.fleksora.dsl

import com.agoda.fleksora.http.Headers
import com.agoda.fleksora.http.Request
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.Retry
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.converter.ConverterFactories

interface Commons {
    val headers: Headers
    val interceptors: Interceptors
    val converterFactories: ConverterFactories

    var retryPolicy: RetryPolicy?
    var fallbackPolicy: FallbackPolicy?

    fun retryPolicy(policy: (Request, Throwable) -> Retry) {
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
