package com.agoda.ninjato.http

import com.agoda.ninjato.dsl.NinjatoDslMarker
import com.agoda.ninjato.misc.add
import com.agoda.ninjato.misc.addAll

/**
 * DSL context for aggregating headers of [request][com.agoda.ninjato.http.Request].
 * Supports additions of single and iterable values of headers mapped to a key.
 */
@NinjatoDslMarker
class Headers {
    @PublishedApi
    internal var parent: Headers? = null

    private val values = mutableMapOf<String, MutableList<String>>()

    /**
     * Adds provided header to the aggregation.
     *
     * @param header key and vale of a header.
     */
    operator fun plusAssign(header: Pair<String, String>) {
        values.add(header)
    }

    /**
     * Adds provided cookie to the aggregation.
     *
     * @param receiver tail lambda with cookie configuration.
     */
    fun cookie(receiver: Cookies.() -> Unit) {
        values.add(Pair(COOKIE, Cookies().apply(receiver).resolve()))
    }

    /**
     * Adds provided header to the aggregation.
     *
     * @param value value of a header.
     */
    infix fun String.to(value: String) {
        values.add(Pair(this, value))
    }

    /**
     * Adds provided headers to the aggregation.
     *
     * @param values values of a header.
     */
    infix fun String.to(value: Iterable<String>) {
        values.addAll(Pair(this, value))
    }

    operator fun invoke(receiver: Headers.() -> Unit) {
        this.apply(receiver)
    }

    @PublishedApi
    internal fun resolve(): Map<String, MutableList<String>>
            = parent?.resolve()?.let { p -> p.toMutableMap().also { it.addAll(values) } } ?: values

    companion object {
        private const val COOKIE = "Cookie"
    }
}
