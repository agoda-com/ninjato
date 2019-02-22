package com.agoda.fleksora.http

open class Headers {
    protected val added: MutableMap<String, String> = mutableMapOf()
    protected val removed: MutableMap<String, String> = mutableMapOf()
    protected val overridden: MutableMap<String, String> = mutableMapOf()

    operator fun plusAssign(header: Pair<String, String>) {
        TODO()
    }

    operator fun minusAssign(header: Pair<String, String>) {
        TODO()
    }

    infix fun override(headers: Map<String, String>) {

    }
}
