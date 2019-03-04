package com.agoda.ninjato.dsl

import com.agoda.ninjato.http.Headers
import com.agoda.ninjato.http.Request
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.Retry
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.converter.ConverterFactories

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
