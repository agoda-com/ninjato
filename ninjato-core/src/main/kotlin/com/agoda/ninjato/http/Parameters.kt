package com.agoda.ninjato.http

import com.agoda.ninjato.dsl.NinjatoDslMarker

/**
 * DSL context for aggregating query parameters of [request][com.agoda.ninjato.http.Request].
 * Supports additions of single value of parameter mapped to a key.
 */
@NinjatoDslMarker
class Parameters {
    @PublishedApi
    internal var parent: Parameters? = null

    private val values = LinkedHashMap<String, String>()

    /**
     * Adds provided parameter to the aggregation.
     *
     * @param parameter key and vale of a parameter.
     */
    operator fun plusAssign(parameter: Pair<String, String>) {
        values[parameter.first] = parameter.second
    }

    operator fun invoke(receiver: Parameters.() -> Unit) {
        this.apply(receiver)
    }

    /**
     * Adds provided parameter to the aggregation.
     *
     * @param value value of a parameter.
     */
    infix fun String.to(value: String) {
        values[this] = value
    }

    @PublishedApi
    internal fun resolve(): MutableMap<String, String>
            = parent?.resolve()?.also { it.putAll(values) } ?: values
}
