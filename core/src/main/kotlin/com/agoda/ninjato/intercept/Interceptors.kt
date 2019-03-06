package com.agoda.ninjato.intercept

import com.agoda.ninjato.http.Request
import com.agoda.ninjato.http.Response
import java.util.*

/**
 * DSL context for aggregating [interceptors][Interceptor].
 * Supports additions and removals of single interceptor as well as setting with ignoring of
 * higher levels of the cascade.
 */
class Interceptors {
    @PublishedApi
    internal var parent: Interceptors? = null

    val add = Aggregator()
    val remove = Aggregator()
    val set = Aggregator()

    /**
     * Adds provided request interceptor to the aggregation.
     *
     * @param interceptor request interceptor to add
     */
    operator fun plusAssign(interceptor: RequestInterceptor) {
        add { interceptor.id to interceptor }
    }

    /**
     * Adds provided response interceptor to the aggregation.
     *
     * @param interceptor response interceptor to add
     */
    operator fun plusAssign(interceptor: ResponseInterceptor) {
        add { interceptor.id to interceptor }
    }

    /**
     * Removes provided request interceptor from the aggregation.
     *
     * @param interceptor request interceptor to remove
     */
    operator fun minusAssign(interceptor: RequestInterceptor) {
        remove { interceptor.id to interceptor }
    }

    /**
     * Removes provided response interceptor from the aggregation.
     *
     * @param interceptor response interceptor to remove
     */
    operator fun minusAssign(interceptor: ResponseInterceptor) {
        remove { interceptor.id to interceptor }
    }

    /**
     * Adds anonymous object of request interceptor to the aggregation.
     *
     * @param id key identifier of the interceptor
     * @param interceptor lambda with interception logic
     */
    fun request(id: String, interceptor: (Request) -> Request) {
        add { id to object : RequestInterceptor() {
            override fun intercept(instance: Request) = interceptor(instance)
        } }
    }

    /**
     * Adds anonymous object of response interceptor to the aggregation.
     *
     * @param id key identifier of the interceptor
     * @param interceptor lambda with interception logic
     */
    fun response(id: String, interceptor: (Response) -> Response) {
        add { id to object : ResponseInterceptor() {
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
