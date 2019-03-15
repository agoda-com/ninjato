package com.agoda.ninjato.dsl

import com.agoda.ninjato.http.Headers
import com.agoda.ninjato.http.Request
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.Retry
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.converter.ConverterFactories
import com.agoda.ninjato.http.Parameters

/**
 * The main DSL entry of the library.
 *
 * This interface provides abilities to manipulate [headers][Headers], [interceptors][Interceptors],
 * [converter factories][ConverterFactories], [retry policies][RetryPolicy] and [fallback policies][FallbackPolicy].
 *
 * This interface is reused on 3 different levels:
 *  - [HttpClient][com.agoda.ninjato.http.HttpClient]
 *  - [Api][com.agoda.ninjato.Api]
 *  - [Request.Configurator]
 *
 *  This makes the DSL cascade: all the values are aggregated across all three levels to form the final values.
 */
interface Commons {
    val headers: Headers
    val parameters: Parameters
    val interceptors: Interceptors
    val converterFactories: ConverterFactories

    var retryPolicy: RetryPolicy?
    var fallbackPolicy: FallbackPolicy?

    /**
     * Creates anonymous implementation of [RetryPolicy] and sets it as current.
     *
     * @param policy lambda with retry policy evaluation.
     */
    fun retryPolicy(policy: (Request, Throwable) -> Retry) {
        retryPolicy = object : RetryPolicy() {
            override fun evaluate(request: Request, throwable: Throwable) = policy(request, throwable)
        }
    }

    /**
     * Creates anonymous implementation of [FallbackPolicy] and sets it as current.
     *
     * @param policy lambda with fallback policy evaluation.
     */
    fun fallbackPolicy(policy: (Request, Throwable) -> Request) {
        fallbackPolicy = object : FallbackPolicy() {
            override fun evaluate(request: Request, throwable: Throwable) = policy(request, throwable)
        }
    }
}
