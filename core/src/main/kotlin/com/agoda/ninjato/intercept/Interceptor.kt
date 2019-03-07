package com.agoda.ninjato.intercept

interface Interceptor<T> {
    fun intercept(instance: T): T
}
