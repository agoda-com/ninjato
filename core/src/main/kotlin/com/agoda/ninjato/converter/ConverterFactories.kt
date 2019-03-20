package com.agoda.ninjato.converter

import com.agoda.ninjato.dsl.NinjatoDslMarker
import java.util.*

/**
 * DSL context for aggregating [converter factories][BodyConverter.Factory].
 * Supports additions of single factory and iterables of factories.
 *
 * When resolving all the aggregations across the DSL cascade, keep in mind,
 * that factories that were defined on the lowest level will come first.
 * This is done to support overriding of converters.
 */
@NinjatoDslMarker
class ConverterFactories {
    @PublishedApi
    internal var parent: ConverterFactories? = null

    private val added: MutableList<BodyConverter.Factory> = LinkedList()

    /**
     * Adds provided factory to the aggregation.
     *
     * @param factory factory to add.
     */
    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    /**
     * Adds provided factories to the aggregation.
     *
     * @param factories list of factories to add.
     */
    operator fun plusAssign(factories: Iterable<BodyConverter.Factory>) {
        added.addAll(factories)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        return (parent?.resolve() ?: LinkedList()).apply { addAll(0, added) }
    }
}
