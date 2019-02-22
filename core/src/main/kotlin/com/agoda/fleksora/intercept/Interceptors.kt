package com.agoda.fleksora.intercept

open class Interceptors {
    protected val added: MutableList<Interceptor<*>> = mutableListOf()
    protected val removed: MutableList<Interceptor<*>> = mutableListOf()
    protected val overridden: MutableList<Interceptor<*>> = mutableListOf()

    operator fun plusAssign(interceptor: Interceptor<*>) {
        TODO()
    }

    operator fun minusAssign(interceptor: Interceptor<*>) {
        TODO()
    }

    infix fun override(headers: Map<String, String>) {

    }
}
