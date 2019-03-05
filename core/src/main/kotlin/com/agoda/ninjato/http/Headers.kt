package com.agoda.ninjato.http

import com.agoda.ninjato.misc.add
import com.agoda.ninjato.misc.addAll
import com.agoda.ninjato.misc.removeAll

class Headers {
    @PublishedApi
    internal var parent: Headers? = null

    val add = Aggregator()
    val override = Aggregator()
    val remove = Aggregator()
    val set = Aggregator()

    operator fun plusAssign(header: Pair<String, String>) {
        add { header.first to header.second }
    }

    operator fun minusAssign(header: Pair<String, String>) {
        remove { header.first to header.second }
    }

    operator fun minusAssign(header: String) {
        remove { header to "" }
    }

    operator fun invoke(receiver: Headers.() -> Unit) {
        this.apply(receiver)
    }

    class Aggregator {
        internal val aggregation: MutableMap<String, MutableList<String>> = mutableMapOf()

        infix fun String.to(value: String) {
            aggregation.add(Pair(this, value))
        }

        infix fun String.to(value: List<String>) {
            aggregation.addAll(Pair(this, value))
        }

        operator fun invoke(receiver: Aggregator.() -> Unit) {
            this.apply(receiver)
        }
    }

    @PublishedApi
    internal fun resolve(): MutableMap<String, MutableList<String>> {
        return if (set.aggregation.isNotEmpty()) set.aggregation else (parent?.resolve() ?: mutableMapOf()).apply {
            addAll(add.aggregation)
            putAll(override.aggregation)
            removeAll(remove.aggregation.filter { !it.value.contains("") })
            remove.aggregation.filter { it.value.contains("") }.keys.forEach { remove(it) }
        }
    }
}
