package com.agoda.fleksora.converter

class ConverterFactories {
    @PublishedApi
    internal var parent: ConverterFactories? = null

    private val added: MutableList<BodyConverter.Factory> = mutableListOf()

    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    operator fun plusAssign(factories: List<BodyConverter.Factory>) {
        added.addAll(factories)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        return (parent?.resolve() ?: mutableListOf()).apply { addAll(added) }
    }
}
