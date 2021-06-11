package com.agoda.ninjato.http

import com.agoda.ninjato.dsl.Commons
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.converter.ConverterFactories
import kotlinx.coroutines.Deferred

/**
 * Abstraction of http client. Takes the top level in the cascade of
 * [commons][Commons] DSL features.
 * Executes generated requests[Request] and returns [responses][Response] in synchronous manner.
 *
 * @see Commons
 *
 * @param requestFactory factory for building [request][Request] entities. `null` by default.
 * @param responseFactory factory for building [response][Response] entities. `null` by default.
 * @param config tail lambda with receiver that allows to configure created instance on the fly.
 */
abstract class HttpClient(
        protected var requestFactory: Request.Factory? = null,
        protected var responseFactory: Response.Factory? = null,
        config: (HttpClient.() -> Unit) = {}
) : Commons {

    final override val headers = Headers()
    final override val parameters = Parameters()
    final override val interceptors = Interceptors()
    final override val converterFactories = ConverterFactories()

    final override var retryPolicy: RetryPolicy? = null
    final override var fallbackPolicy: FallbackPolicy? = null

    init { this.apply(config) }

    /**
     * Executes generated requests[Request] and returns [responses][Response] in synchronous manner.
     * All thrown exceptions from this function will trigger [retry][RetryPolicy] and [fallback][FallbackPolicy] policies.
     *
     * @param request library's request entity
     * @return library's response entity
     */
    abstract fun execute(request: Request): Response

    /**
     * Executes generated requests[Request] and returns [responses][Response] in asynchronous manner.
     * All thrown exceptions from this function will trigger [retry][RetryPolicy] and [fallback][FallbackPolicy] policies.
     *
     * @param request library's request entity
     * @return library's response entity
     */
    abstract suspend fun executeAsync(request: Request): Response

    @PublishedApi
    internal fun request() = requestFactory?.create() ?: Request()

    operator fun invoke(receiver: HttpClient.() -> Unit) {
        apply(receiver)
    }
}
