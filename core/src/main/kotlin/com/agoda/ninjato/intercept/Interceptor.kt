package com.agoda.ninjato.intercept

/**
 * Base interceptor interface that allows custom logic before [request][com.agoda.ninjato.http.Request]
 * is executed and after [response][com.agoda.ninjato.http.Response] is retrieved.
 */
interface Interceptor<T> {
    /**
     * Key identifier of the interceptor.
     * This property is introduced to support removals on the lower levels of the DSL cascade of aggregation.
     */
    var id: String

    /**
     * Intercepting function. Has the chance to modify the provided value or return a new one of the same type.
     *
     * @param instance object to intercept
     * @return intercepted instance
     */
    fun intercept(instance: T): T
}
