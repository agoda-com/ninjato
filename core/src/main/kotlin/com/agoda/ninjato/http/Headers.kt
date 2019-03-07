package com.agoda.ninjato.http

import com.agoda.ninjato.misc.add
import com.agoda.ninjato.misc.addAll

class Headers {
    @PublishedApi
    internal var parent: Headers? = null

    private val values = mutableMapOf<String, MutableList<String>>()

    operator fun plusAssign(header: Pair<String, String>) {
        values.add(header)
    }

    operator fun invoke(receiver: Headers.() -> Unit) {
        this.apply(receiver)
    }

    infix fun String.to(value: String) {
        values.add(Pair(this, value))
    }

    infix fun String.to(value: Iterable<String>) {
        values.addAll(Pair(this, value))
    }

    @PublishedApi
    internal fun resolve(): MutableMap<String, MutableList<String>>
            = parent?.resolve()?.also { it.addAll(values) } ?: values
}
