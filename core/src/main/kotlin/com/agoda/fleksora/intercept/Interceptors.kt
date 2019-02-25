package com.agoda.fleksora.intercept

open class Interceptors {
    protected val added: MutableList<Interceptor<*>> = mutableListOf()
    protected val removed: MutableList<Interceptor<*>> = mutableListOf()
    protected val overridden: MutableList<Interceptor<*>> = mutableListOf()

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

    infix fun override(interceptors: List<Interceptor<*>>) {
        interceptors.forEach { verify(it) }

        with(overridden) {
            clear()
            addAll(interceptors)
        }
    }

    private fun verify(interceptor: Interceptor<*>) {
        if (interceptor !is RequestInterceptor && interceptor !is ResponseInterceptor) {
            throw IllegalArgumentException("Provided interceptor does not extend RequestInterceptor and ResponseInterceptor!")
        }
    }
}
