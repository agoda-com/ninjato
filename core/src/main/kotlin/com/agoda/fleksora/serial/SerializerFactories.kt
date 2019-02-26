package com.agoda.fleksora.serial

class SerializerFactories {
    protected val added: MutableList<Serializer.Factory> = mutableListOf()

    operator fun plusAssign(factory: Serializer.Factory) {
        added.add(factory)
    }
}
