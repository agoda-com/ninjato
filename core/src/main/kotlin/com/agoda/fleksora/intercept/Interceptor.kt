package com.agoda.fleksora.intercept

interface Interceptor<T> {
    fun intercept(instance: T): T
}
