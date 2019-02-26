package com.agoda.fleksora.intercept

class Interceptors {
    internal var parent: Interceptors? = null

    private val added: MutableList<Interceptor<*>> = mutableListOf()
    private val removed: MutableList<Interceptor<*>> = mutableListOf()
    private val overridden: MutableList<Interceptor<*>> = mutableListOf()

    operator fun plusAssign(interceptor: RequestInterceptor) {
        added.add(interceptor)
    }

    operator fun plusAssign(interceptor: ResponseInterceptor) {
        added.add(interceptor)
    }

    operator fun minusAssign(interceptor: RequestInterceptor) {
        removed.add(interceptor)
    }

    operator fun minusAssign(interceptor: ResponseInterceptor) {
        removed.add(interceptor)
    }

    infix fun set(interceptors: List<Interceptor<*>>) {
        interceptors.forEach { verify(it) }

        with(overridden) {
            clear()
            addAll(interceptors)
        }
    }

    internal fun resolve(): MutableList<Interceptor<*>> {
        TODO()
    }

    private fun verify(interceptor: Interceptor<*>) {
        if (interceptor !is RequestInterceptor && interceptor !is ResponseInterceptor) {
            throw IllegalArgumentException("Provided interceptor does not extend RequestInterceptor and ResponseInterceptor!")
        }
    }
}
