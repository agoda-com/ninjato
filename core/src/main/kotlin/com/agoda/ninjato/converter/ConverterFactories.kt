package com.agoda.ninjato.converter

/**
 * DSL context for aggregating [converter factories][BodyConverter.Factory].
 * Supports additions of single factory and list of factories.
 *
 * When resolving all the aggregations across the DSL cascade, keep in mind,
 * that factories that were defined on the lowest level will come first.
 * This is done to support overriding of converters.
 */
class ConverterFactories {
    @PublishedApi
    internal var parent: ConverterFactories? = null

    private val added: MutableList<BodyConverter.Factory> = mutableListOf()

    /**
     * Adds provided factory to the aggregation.
     *
     * @param factory factory to add
     */
    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    /**
     * Adds provided factories to the aggregation.
     *
     * @param factories list of factories to add
     */
    operator fun plusAssign(factories: List<BodyConverter.Factory>) {
        added.addAll(factories)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        return (parent?.resolve() ?: mutableListOf()).apply { addAll(added) }
    }
}
