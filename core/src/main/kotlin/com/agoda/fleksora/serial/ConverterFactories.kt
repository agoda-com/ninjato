package com.agoda.fleksora.serial

class ConverterFactories {
    internal var parent: ConverterFactories? = null

    protected val added: MutableList<BodyConverter.Factory> = mutableListOf()

    operator fun plusAssign(factory: BodyConverter.Factory) {
        added.add(factory)
    }

    @PublishedApi
    internal fun resolve(): MutableList<BodyConverter.Factory> {
        val factories = parent?.resolve() ?: mutableListOf()
        factories.addAll(added)
        return factories
    }
}
