package com.agoda.ninjato.intercept

/**
 * Base interceptor interface that allows custom logic before [request][com.agoda.ninjato.http.Request]
 * is executed and after [response][com.agoda.ninjato.http.Response] is retrieved.
 */
interface Interceptor<T> {
    var id: String
    /**
     * Intercepting function. Has the chance to modify the provided value or return a new one of the same type.
     *
     * @param instance object to intercept
     * @return intercepted instance
     */
    fun intercept(instance: T): T
}
