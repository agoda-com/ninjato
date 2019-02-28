package com.agoda.fleksora.converter

class ConverterFactories {
    @PublishedApi
    internal var parent: ConverterFactories? = null

    private val added: MutableList<BodyConverter.Factory> = mutableListOf()

    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        return (parent?.resolve() ?: mutableListOf()).apply { addAll(added) }
    }
}
