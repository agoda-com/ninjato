package com.agoda.fleksora

import com.agoda.fleksora.dsl.Commons
import com.agoda.fleksora.http.*
import com.agoda.fleksora.intercept.Interceptors
import com.agoda.fleksora.intercept.RequestInterceptor
import com.agoda.fleksora.intercept.ResponseInterceptor
import com.agoda.fleksora.log.Logger
import com.agoda.fleksora.policy.FallbackPolicy
import com.agoda.fleksora.policy.Retry
import com.agoda.fleksora.policy.RetryPolicy
import com.agoda.fleksora.reflect.TypeReference.Companion.reifiedType
import com.agoda.fleksora.serial.BodyConverter
import com.agoda.fleksora.serial.ConverterFactories

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

        var request = client.request()

        request.let {
            it.method = method
            it.baseUrl = baseUrl
        }

        configurator.configure(request)

        val requestInterceptors: List<RequestInterceptor>
        val responseInterceptors: List<ResponseInterceptor>

        configurator.interceptors.resolve().let { interceptors ->
            requestInterceptors = interceptors.filter { it is RequestInterceptor } as List<RequestInterceptor>
            responseInterceptors = interceptors.filter { it is ResponseInterceptor } as List<ResponseInterceptor>
        }

        while (true) {
            try {
                requestInterceptors.forEach { request = it.intercept(request) }
                var response = client.execute(request)
                responseInterceptors.forEach { response = it.intercept(response) }

                return when (T::class) {
                    Unit::class -> Unit as T
                    String::class -> response.body!!.asString as T
                    ByteArray::class -> response.body!!.asByteArray as T
                    else -> {
                        var converter: BodyConverter<Body, T>? = null

                        configurator.converterFactories.resolve().firstOrNull {
                            converter = it.responseConverter(reifiedType<T>()) as? BodyConverter<Body, T>
                            converter != null
                        }?.let {
                            converter!!.convert(response.body!!)
                        } ?: throw UnsupportedOperationException("Couldn't convert response body. Did you registered " +
                                "BodyConverter.Factory that provides serializer for ${T::class.java.simpleName} type?")
                    }
                }
            } catch (throwable: Throwable) {
                configurator.retryPolicy?.let {
                    evaluateRetry(it, request, throwable)
                } ?: retryPolicy?.let {
                    evaluateRetry(it, request, throwable)
                } ?: client.retryPolicy?.let {
                    evaluateRetry(it, request, throwable)
                } ?: throw throwable

                // If we got here, it means that retry policy has requested retry attempt
                configurator.fallbackPolicy?.let {
                    request = it.evaluate(request, throwable)
                } ?: fallbackPolicy?.let {
                    request = it.evaluate(request, throwable)
                } ?: client.fallbackPolicy?.let {
                    request = it.evaluate(request, throwable)
                }

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

    open class Configurator {
        lateinit var httpClient: HttpClient
        var logger: Logger? = null

        open fun configure(instance: Api) = instance.also {
            it.client = httpClient
            it.logger = logger

            with(instance) {
                headers.parent = client.headers
                interceptors.parent = client.interceptors
                converterFactories.parent = client.converterFactories
            }
        }
    }

    companion object {
        fun configure(instance: Api, builder: Api.Configurator.() -> Unit)
                = Configurator().apply(builder).configure(instance)
    }
}
