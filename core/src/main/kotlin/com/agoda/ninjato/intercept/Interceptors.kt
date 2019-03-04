package com.agoda.ninjato.intercept

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import java.util.*

class Interceptors {
    @PublishedApi
    internal var parent: Interceptors? = null

    val add = Aggregator()
    val remove = Aggregator()
    val set = Aggregator()

    operator fun plusAssign(interceptor: RequestInterceptor) {
        add { interceptor.id to interceptor }
    }

    operator fun plusAssign(interceptor: ResponseInterceptor) {
        add { interceptor.id to interceptor }
    }

    operator fun minusAssign(interceptor: RequestInterceptor) {
        remove { interceptor.id to interceptor }
    }

    operator fun minusAssign(interceptor: ResponseInterceptor) {
        remove { interceptor.id to interceptor }
    }

    fun request(interceptor: (Request) -> Request) {
        add { UUID.randomUUID().toString() to object : RequestInterceptor() {
            override fun intercept(instance: Request) = interceptor(instance)
        } }
    }

    fun response(interceptor: (Response) -> Response) {
        add { UUID.randomUUID().toString() to object : ResponseInterceptor() {
            override fun intercept(instance: Response) = interceptor(instance)
        } }
    }

    operator fun invoke(receiver: Interceptors.() -> Unit) {
        this.apply(receiver)
    }

    class Aggregator {
        internal val aggregation: MutableSet<Interceptor<*>> = mutableSetOf()

        infix fun String.to(value: Interceptor<*>) {
            aggregation.add(value.also { it.id = this })
        }

        operator fun invoke(receiver: Aggregator.() -> Unit) {
            this.apply(receiver)
        }
    }

    @PublishedApi
    internal fun resolve(): MutableSet<Interceptor<*>> {
        return if (set.aggregation.isNotEmpty()) set.aggregation else (parent?.resolve() ?: mutableSetOf()).apply {
            addAll(add.aggregation)
            removeAll(remove.aggregation)
        }
    }

}
