package com.agoda.ninjato.converter

import java.util.*

class ConverterFactories {
    @PublishedApi
    internal var parent: ConverterFactories? = null

    private val added: MutableList<BodyConverter.Factory> = LinkedList()

    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    operator fun plusAssign(factories: Iterable<BodyConverter.Factory>) {
        added.addAll(factories)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        return (parent?.resolve() ?: LinkedList()).apply { addAll(0, added) }
    }
}
