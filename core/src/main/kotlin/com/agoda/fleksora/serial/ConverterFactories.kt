package com.agoda.fleksora.serial

class ConverterFactories {
    internal var parent: ConverterFactories? = null

    protected val added: MutableList<BodyConverter.Factory> = mutableListOf()

    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        return (parent?.resolve() ?: mutableListOf()).apply { addAll(added) }
    }
}
