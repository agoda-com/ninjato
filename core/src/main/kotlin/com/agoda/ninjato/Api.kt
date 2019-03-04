package com.agoda.ninjato

import com.agoda.ninjato.dsl.Commons
import com.agoda.ninjato.intercept.Interceptors
import com.agoda.ninjato.intercept.RequestInterceptor
import com.agoda.ninjato.intercept.ResponseInterceptor
import com.agoda.ninjato.log.Logger
import com.agoda.ninjato.policy.FallbackPolicy
import com.agoda.ninjato.policy.Retry
import com.agoda.ninjato.policy.RetryPolicy
import com.agoda.ninjato.reflect.TypeReference.Companion.reifiedType
import com.agoda.ninjato.converter.BodyConverter
import com.agoda.ninjato.converter.ConverterFactories
import com.agoda.ninjato.exception.NinjatoException
import com.agoda.ninjato.http.*
import com.agoda.ninjato.log.Level
import com.agoda.ninjato.policy.Policy

abstract class Api : Commons {
    override val headers = Headers()
    override val interceptors = Interceptors()
    override val converterFactories = ConverterFactories()

    override var retryPolicy: RetryPolicy? = null
    override var fallbackPolicy: FallbackPolicy? = null

    @PublishedApi
    internal lateinit var client: HttpClient

    @PublishedApi
    internal var logger: Logger? = null

    abstract val baseUrl: String

    inline fun <reified T> get(configurator: Request.Configurator.() -> Unit): T = prepare(Method.Get, configurator)
    inline fun <reified T> head(configurator: Request.Configurator.() -> Unit): T = prepare(Method.Head, configurator)
    inline fun <reified T> post(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Post, configurator)
    inline fun <reified T> put(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Put, configurator)
    inline fun <reified T> delete(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Delete, configurator)
    inline fun <reified T> options(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Options, configurator)
    inline fun <reified T> patch(configurator: Request.Configurator.WithBody.() -> Unit): T = prepareWithBody(Method.Patch, configurator)

    @PublishedApi
    internal inline fun <reified T> prepare(method: Method, configurator: Request.Configurator.() -> Unit): T {
        val config = Request.Configurator()

        config.let {
            it.headers.parent = headers
            it.interceptors.parent = interceptors
            it.converterFactories.parent = converterFactories
        }

        return call(method, config.apply(configurator))
    }

    @PublishedApi
    internal inline fun <reified T> prepareWithBody(method: Method, configurator: Request.Configurator.WithBody.() -> Unit): T {
        val config = Request.Configurator.WithBody()

        config.let {
            it.headers.parent = headers
            it.interceptors.parent = interceptors
            it.converterFactories.parent = converterFactories
        }

        return call(method, config.apply(configurator))
    }

    @PublishedApi
    @Suppress("UNCHECKED_CAST")
    internal inline fun <reified T> call(method: Method, configurator: Request.Configurator): T {
        logger?.log(Level.Verbose, "Executing a new call...")

        var request = client.request()

        request.let {
            it.method = method
            it.baseUrl = baseUrl
        }

        configurator.configure(request)

        logger?.log(Level.Verbose, "New call url is ${request.url}")

        val requestInterceptors: List<RequestInterceptor>
        val responseInterceptors: List<ResponseInterceptor>

        configurator.interceptors.resolve().let { interceptors ->
            requestInterceptors = interceptors.filter { it is RequestInterceptor } as List<RequestInterceptor>
            responseInterceptors = interceptors.filter { it is ResponseInterceptor } as List<ResponseInterceptor>

            logger?.let {
                if (interceptors.size > requestInterceptors.size + responseInterceptors.size) {
                    it.log(
                            Level.Warning,
                            "Amount of resolved interceptors for request with url ${request.url} is less than actual provided amount.\n" +
                            "Have you added interceptors that do not extend RequestInterceptor and ResponseInterceptor?"
                    )
                }
            }
        }

        while (true) {
            try {
                logger?.log(Level.Debug, "Invoking request interceptors for request with url ${request.url}")
                requestInterceptors.forEach {
                    logger?.log(Level.Debug, "Invoking $it RequestInterceptor for request with url ${request.url}" )
                    request = it.intercept(request)
                }

                logger?.log(Level.Debug, "Passing request with url ${request.url} to HttpClient")
                var response = client.execute(request)
                logger?.log(Level.Debug, "HttpClient returned response for request with url ${request.url} -> $response")

                logger?.log(Level.Debug, "Invoking response")
                responseInterceptors.forEach {
                    logger?.log(Level.Debug, "Invoking $it ResponseInterceptor for request with url ${request.url}")
                    response = it.intercept(response)
                }

                return when (T::class) {
                    Unit::class -> Unit as T
                    String::class -> response.body!!.asString as T
                    ByteArray::class -> response.body!!.asByteArray as T
                    else -> {
                        var converter: BodyConverter<Body, T>? = null

                        logger?.log(Level.Debug, "Request with url ${request.url} requires BodyConverter, trying to require instance")
                        configurator.converterFactories.resolve().firstOrNull {
                            converter = it.responseConverter(reifiedType<T>()) as? BodyConverter<Body, T>
                            converter != null
                        }?.let {
                            logger?.log(Level.Debug, "BodyConverter has been found for request with url ${request.url} -> $converter")
                            converter!!.convert(response.body!!)
                        } ?: throw NinjatoException(
                                request.url,
                                UnsupportedOperationException("Couldn't convert response body. Did you registered " +
                                        "BodyConverter.Factory that provides serializer for ${T::class.java.simpleName} type?"
                                )
                        )
                    }
                }
            } catch (throwable: Throwable) {
                logger?.log(Level.Error, "Problem has occurred while serving request with url ${request.url}", throwable)

                if (throwable is NinjatoException) throw throwable

                configurator.retryPolicy?.let {
                    logPolicy("retry", request.url, it)
                    evaluateRetry(it, request, throwable)
                } ?: retryPolicy?.let {
                    logPolicy("retry", request.url, it)
                    evaluateRetry(it, request, throwable)
                } ?: client.retryPolicy?.let {
                    logPolicy("retry", request.url, it)
                    evaluateRetry(it, request, throwable)
                } ?: throw throwable

                // If we got here, it means that retry policy has requested retry attempt
                configurator.fallbackPolicy?.let {
                    logPolicy("fallback", request.url, it)
                    request = it.evaluate(request, throwable)
                } ?: fallbackPolicy?.let {
                    logPolicy("fallback", request.url, it)
                    request = it.evaluate(request, throwable)
                } ?: client.fallbackPolicy?.let {
                    logPolicy("fallback", request.url, it)
                    request = it.evaluate(request, throwable)
                }

                logger?.log(
                        Level.Debug,
                        "Request with url ${request.url} has been scheduled for retry\n" +
                                "State after evaluating fallback policies -> $request"
                )

                request.retries++
            }
        }
    }

    @PublishedApi
    internal fun evaluateRetry(policy: RetryPolicy, request: Request, throwable: Throwable) {
        val evaluation = policy.evaluate(request, throwable)

        when (evaluation) {
            is Retry.DoNotRetry -> throw throwable
            is Retry.WithDelay -> Thread.sleep(evaluation.delay)
        }
    }

    @PublishedApi
    internal fun logPolicy(type: String, url: String, policy: Policy<*>) {
        logger?.log(Level.Debug, "Evaluating $type policy for request with url $url -> $policy")
    }

    open class Configurator {
        lateinit var httpClient: HttpClient
        var logger: Logger? = null

        internal fun configure(instance: Api) = instance.also {
            it.client = httpClient
            it.logger = logger

            with(instance) {
                headers.parent = client.headers
                interceptors.parent = client.interceptors
                converterFactories.parent = client.converterFactories
            }

            logger?.log(
                    Level.Info,
                    "Configuring Api -> $instance\n" +
                            "HttpClient -> $httpClient\n"
            )
        }
    }

    companion object {
        fun configure(instance: Api, builder: Configurator.() -> Unit)
                = Configurator().apply(builder).configure(instance)
    }
}
