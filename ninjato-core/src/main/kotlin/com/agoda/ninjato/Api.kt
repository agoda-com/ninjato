package com.agoda.ninjato

import com.agoda.ninjato.dsl.Commons
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.Retry
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.reflect.TypeReference.Companion.reifiedType
import com.agoda.ninjato.converter.BodyConverter
import com.agoda.ninjato.converter.ConverterFactories
import com.agoda.ninjato.exception.MissingBodyException
import com.agoda.ninjato.exception.HttpException
import com.agoda.ninjato.exception.MissingConverterException
import com.agoda.ninjato.http.*

/**
 * Main class of the library.
 * Provides access to the DSL of call functions.
 *
 * @param client http client to forward calls to.
 * @param config tail lambda with receiver that allows to configure created instance on the fly.
 */
abstract class Api(
        @PublishedApi internal val client: HttpClient,
        config: Api.() -> Unit = {}
) : Commons {
    final override val headers = Headers()
    final override val parameters = Parameters()
    final override val interceptors = Interceptors()
    final override val converterFactories = ConverterFactories()

    final override var retryPolicy: RetryPolicy? = null
    final override var fallbackPolicy: FallbackPolicy? = null

    abstract val baseUrl: String

    init {
        this.apply(config)

        headers.parent = client.headers
        parameters.parent = client.parameters
        interceptors.parent = client.interceptors
        converterFactories.parent = client.converterFactories
    }

    /**
     * Executes the GET request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> get(configurator: Request.Configurator.() -> Unit): T = prepare(Method.Get, configurator)

    /**
     * Executes the GET request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> getAsync(configurator: Request.Configurator.() -> Unit): T = prepareAsync(Method.Get, configurator)

    /**
     * Executes the HEAD request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> head(configurator: Request.Configurator.() -> Unit): T = prepare(Method.Head, configurator)

    /**
     * Executes the HEAD request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> headAsync(configurator: Request.Configurator.() -> Unit): T = prepareAsync(Method.Head, configurator)

    /**
     * Executes the POST request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> post(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Post, configurator)

    /**
     * Executes the POST request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> postAsync(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBodyAsync(Method.Post, configurator)

    /**
     * Executes the PUT request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> put(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Put, configurator)

    /**
     * Executes the PUT request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> putAsync(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBodyAsync(Method.Put, configurator)

    /**
     * Executes the DELETE request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> delete(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Delete, configurator)

    /**
     * Executes the DELETE request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> deleteAsync(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBodyAsync(Method.Delete, configurator)

    /**
     * Executes the OPTIONS request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> options(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Options, configurator)

    /**
     * Executes the OPTIONS request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> optionsAsync(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBodyAsync(Method.Options, configurator)

    /**
     * Executes the PATCH request with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    inline fun <reified T> patch(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Patch, configurator)

    /**
     * Executes the PATCH request in coroutine context with the provided configuration.
     *
     * @param configurator the configuration of a [Request]
     */
    suspend inline fun <reified T> patchAsync(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBodyAsync(Method.Patch, configurator)

    @PublishedApi
    internal inline fun <reified T> prepare(method: Method, configurator: Request.Configurator.() -> Unit): T {
        val config = Request.Configurator()

        config.let {
            it.headers.parent = headers
            it.parameters.parent = parameters
            it.interceptors.parent = interceptors
            it.converterFactories.parent = converterFactories
        }

        return call(method, config.apply(configurator))
    }

    @PublishedApi
    internal suspend inline fun <reified T> prepareAsync(method: Method, configurator: Request.Configurator.() -> Unit): T {
        val config = Request.Configurator()

        config.let {
            it.headers.parent = headers
            it.parameters.parent = parameters
            it.interceptors.parent = interceptors
            it.converterFactories.parent = converterFactories
        }

        return callAsync(method, config.apply(configurator))
    }

    @PublishedApi
    internal inline fun <reified T> prepareWithBody(method: Method, configurator: Request.Configurator.WithBody.() -> Unit): T {
        val config = Request.Configurator.WithBody()

        config.let {
            it.headers.parent = headers
            it.parameters.parent = parameters
            it.interceptors.parent = interceptors
            it.converterFactories.parent = converterFactories
        }

        return call(method, config.apply(configurator))
    }

    @PublishedApi
    internal suspend inline fun <reified T> prepareWithBodyAsync(method: Method, configurator: Request.Configurator.WithBody.() -> Unit): T {
        val config = Request.Configurator.WithBody()

        config.let {
            it.headers.parent = headers
            it.parameters.parent = parameters
            it.interceptors.parent = interceptors
            it.converterFactories.parent = converterFactories
        }

        return callAsync(method, config.apply(configurator))
    }

    @PublishedApi
    internal inline fun <reified T> call(method: Method, configurator: Request.Configurator): T {
        var request = setupRequest(method, configurator)

        val requestInterceptors = configurator.interceptors.resolveRequest()
        val responseInterceptors = configurator.interceptors.resolveResponse()

        while (true) {
            try {
                for (interceptor in requestInterceptors) {
                    request = interceptor.intercept(request)
                }

                var response = client.execute(request).also { it.request = request }

                for (interceptor in responseInterceptors) {
                    response = interceptor.intercept(response)
                }

                return parseResponse(request, configurator, response)
            } catch (throwable: Throwable) {
                request = parseThrowable(request, configurator, throwable)
            }
        }
    }
    
    @PublishedApi
    internal suspend inline fun <reified T> callAsync(method: Method, configurator: Request.Configurator): T {
        var request = setupRequest(method, configurator)

        val requestInterceptors = configurator.interceptors.resolveRequest()
        val responseInterceptors = configurator.interceptors.resolveResponse()

        while (true) {
            try {
                for (interceptor in requestInterceptors) {
                    request = interceptor.intercept(request)
                }

                var response = client.executeAsync(request).also { it.request = request }

                for (interceptor in responseInterceptors) {
                    response = interceptor.intercept(response)
                }

                return parseResponse(request, configurator, response)
            } catch (throwable: Throwable) {
                request = parseThrowable(request, configurator, throwable)
            }
        }
    }
    
    @PublishedApi
    internal fun setupRequest(method: Method, configurator: Request.Configurator): Request {
        val request = client.request()

        request.method = method
        request.baseUrl = baseUrl

        configurator.configure(request)

        if (request.method.requiresBody && request.body == null) {
            throw MissingBodyException(request)
        }
        
        return request
    }
    
    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal inline fun <reified T> parseResponse(request: Request, configurator: Request.Configurator, response: Response): T {
        // We are throwing exception only if non-response return type is implied
        // Since in non-response case we can't inform the user that something happened
        // with his request and most probably response body transformation will not go as
        // planned and we might result in another exception
        if (!response.isSuccess && T::class != Response::class) {
            throw HttpException(request, response)
        }

        return when (T::class) {
            Unit::class -> Unit as T
            Response::class -> response as T
            Body::class -> response.body!! as T
            String::class -> response.body!!.asString as T
            ByteArray::class -> response.body!!.asByteArray as T
            else -> {
                val body = response.body!!
                var converted: T? = null

                for (factory in configurator.converterFactories.resolve()) {
                    val converter = factory.responseConverter(reifiedType<T>()) as? BodyConverter<Body, T>

                    if (converter != null) {
                        converted = converter.convert(body)
                        break
                    }
                }

                converted ?: throw MissingConverterException(request.url, T::class.java.simpleName)
            }
        }
    }
    
    @PublishedApi
    internal fun parseThrowable(request: Request, configurator: Request.Configurator, throwable: Throwable): Request {
        when (throwable) {
            is MissingBodyException -> throw throwable
            is MissingConverterException -> throw throwable
        }

        val retry = configurator.retryPolicy ?: retryPolicy ?: client.retryPolicy ?: throw throwable

        when (val evaluation = retry.evaluate(request, throwable)) {
            is Retry.DoNotRetry -> throw throwable
            is Retry.WithDelay -> evaluation.delay()
        }

        val fallback = configurator.fallbackPolicy ?: fallbackPolicy ?: client.fallbackPolicy

        return (fallback?.evaluate(request, throwable) ?: request).also { it.retries++ }
    }

    operator fun invoke(receiver: Api.() -> Unit) {
        apply(receiver)
    }
    
    suspend fun async(receiver: suspend Api.() -> Unit) {
        receiver(this)
    }
}
